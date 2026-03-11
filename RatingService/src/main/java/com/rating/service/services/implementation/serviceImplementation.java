package com.rating.service.services.implementation;

import com.rating.service.entities.Rating;
import com.rating.service.exception.ResourceNotFoundException;
import com.rating.service.repository.RatingRepository;
import com.rating.service.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class serviceImplementation implements RatingService {

    private final RatingRepository  ratingRepository;
    @Override
    public Rating getRating(Rating rating) {
        String uuid= UUID.randomUUID().toString();
        rating.setRatingId(uuid);
      return ratingRepository.save(rating);
    }


@Override
public Rating getRatingById(String ratingId) {

    return ratingRepository.findById(ratingId)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Rating not found with id: " + ratingId));
}

    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingByUserId(String userId) {
        return ratingRepository.getAllRatingByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingByHotelId(String hotelId) {
      return ratingRepository.getAllRatingByHotelId(hotelId);
    }
}
