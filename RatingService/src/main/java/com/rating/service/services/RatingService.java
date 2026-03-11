package com.rating.service.services;

import com.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    // create rating
    Rating getRating(Rating rating);

    //get rating by rating Id
    Rating getRatingById(String ratingId);

    // get all rating
    List<Rating> getRatings();

    // get all rating using userID
    List<Rating> getAllRatingByUserId(String userId);

    //get all rating using hotelId
    List<Rating> getAllRatingByHotelId(String hotelId);


}
