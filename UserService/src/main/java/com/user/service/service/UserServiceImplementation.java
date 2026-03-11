package com.user.service.service;

import com.user.service.entities.Hotel;
import com.user.service.entities.Rating;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.externalServicesFeignClient.HotelService;
import com.user.service.externalServicesFeignClient.RatingService;
import com.user.service.repositories.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService {


    //use restTemplte to communicate between two microservices
//    private final RestTemplate restTemplate;

    private final UserRepository userRepository;
    private final HotelService hotelService;
    private final RatingService ratingService;

    @Override
    public User createUser(User user) {
        String uuid = UUID.randomUUID().toString();
        user.setUserId(uuid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //    @Override
//    public User getUserById(String userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
//
//        // getting ratings of user using userId
//        Rating[] ratingsOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getUserId(), Rating[].class);
//        assert ratingsOfUser != null;
//        user.setRatings(List.of(ratingsOfUser));
//        return user;
//    }


    @Override
//    @Retry(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")

    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @RateLimiter(name = "userRatingHotel", fallbackMethod = "rateLimiterRatingHotelFallback")
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Call Rating Service using restTemplate
//        Rating[] ratings = restTemplate.getForObject(
//                "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
//                Rating[].class

        //using feign client
        Rating[] ratings = ratingService.getRatings(user.getUserId()).toArray(new Rating[0]);
        //);

        if (ratings != null) {
            for (Rating rating : ratings) {
                try {
                    //using feign client
                    Hotel hotel = hotelService.getHotel(rating.getHotelId());
                    // Call Hotel Service using restTemplate
//                    Hotel hotel = restTemplate.getForObject(
//                            "http://HOTELSERVICE/hotels/" + rating.getHotelId(),
//                            Hotel.class
                    rating.setHotel(hotel);
                } catch (Exception e) {
                    rating.setHotel(null); // prevent crash
                }
            }
            user.setRatings(Arrays.asList(ratings));
        }
        return user;
    }

    //this is for circuit breaker and retry
    public User ratingHotelFallback(String userId, Exception ex) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setRatings(new ArrayList<>());
        return user;
    }

    // this is only for rate limiter
    public User rateLimiterRatingHotelFallback(String userId, Exception ex) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        log.info("Rate limit exceeded. Returning dummy user.");
        user.setUserId("RATE_LIMITED");
        user.setName("Achha user");
        user.setEmail("achabeta@example.com");
        user.setAbout("This is achha data because rate limit exceeded");
        user.setRatings(new ArrayList<>());
        return user;
    }


    @Override
    public void deleteById(String userId) {
        userRepository.deleteById(userId);
    }
}
