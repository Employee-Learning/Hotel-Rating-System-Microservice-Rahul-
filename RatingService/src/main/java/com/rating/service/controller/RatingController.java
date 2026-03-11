package com.rating.service.controller;

import com.rating.service.entities.Rating;
import com.rating.service.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;
    //create rating
    @PostMapping
    public ResponseEntity<Rating> addRating(@RequestBody Rating rating) {
       Rating rating1= ratingService.getRating(rating);
       return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    // get rating by rating id
    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getRatingById(
            @PathVariable String ratingId) {
        Rating rating = ratingService.getRatingById(ratingId);
        return ResponseEntity.ok(rating);
    }

    // get all ratings
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings()
    {
        List<Rating> ratings= ratingService.getRatings();
        return ResponseEntity.ok(ratings);
    }
    // get all rating by user id
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId)
    {
        List<Rating> ratings= ratingService.getAllRatingByUserId(userId);
        return ResponseEntity.ok(ratings);
    }

    //get all rating by hotel id
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId)
    {
        List<Rating> ratings= ratingService.getAllRatingByHotelId(hotelId);
        return ResponseEntity.ok(ratings);
    }
}
