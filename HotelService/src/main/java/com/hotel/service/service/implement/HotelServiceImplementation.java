package com.hotel.service.service.implement;

import com.hotel.service.entities.Hotel;
import com.hotel.service.entities.Rating;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.external.services.RatingService;
import com.hotel.service.external.services.UserService;
import com.hotel.service.payload.User;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.service.HotelService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImplementation implements HotelService {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpServletRequest request;
    private final HotelRepository  hotelRepository;




    @Override
    public Hotel createHotel(Hotel hotel) {
        String uuid = UUID.randomUUID().toString();
        hotel.setHotelId(uuid);
        return hotelRepository.save(hotel);
    }


    //    @Override
//    public Hotel getHotelById(String hotelId) {
//        Hotel hotel= hotelRepository.findById(hotelId)
//                .orElseThrow(()->
//                        new ResourceNotFoundException("Hotel with this id is not present in the database " + hotelId));
//        return hotel;
//    }
@Override
public Hotel getHotelById(String hotelId) {

    Hotel hotel = hotelRepository.findById(hotelId)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));

    List<Rating> ratings = ratingService.getRatingsByHotelId(hotelId);
    if (ratings != null && !ratings.isEmpty()) {
        double avg = ratings.stream()
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0);
        hotel.setAverageRating(avg);
    } else {
        hotel.setAverageRating(0.0);
    }
    return hotel;
}


    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        hotels.forEach(hotel -> {
//            Rating[] ratings = restTemplate.getForObject(
//                    "http://localhost:8083/ratings/hotels/" + hotel.getHotelId(),
//                    Rating[].class
//            );
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", request.getHeader("Authorization"));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Rating[]> response =
                    restTemplate.exchange(
                            "http://localhost:8083/ratings/hotels/" + hotel.getHotelId(),
                            HttpMethod.GET,
                            entity,
                            Rating[].class
                    );
            Rating[] ratings = response.getBody();
            if (ratings != null && ratings.length > 0) {

                double avgRating = Arrays.stream(ratings)
                        .mapToInt(Rating::getRating)
                        .average()
                        .orElse(0);
                hotel.setAverageRating(avgRating);
            } else {
                hotel.setAverageRating(0.0);
            }
        });
        return hotels;
    }


    @Override
    public void deleteById(String hotelId) {

        if (!hotelRepository.existsById(hotelId)) {
            throw new ResourceNotFoundException("Hotel not found with id: " + hotelId);
        }
        hotelRepository.deleteById(hotelId);
    }

    @Override
    public List<Rating> getHotelRatings(String hotelId) {

        List<Rating> ratings = ratingService.getRatingsByHotelId(hotelId);
        ratings.forEach(rating -> {
            try {
                User user = userService.getUser(rating.getUserId());
                rating.setUser(user);
            } catch (Exception e) {
                rating.setUser(null);
            }
        });
        return ratings;
    }
}
