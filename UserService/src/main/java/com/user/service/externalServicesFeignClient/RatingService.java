package com.user.service.externalServicesFeignClient;

import com.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "RATINGSERVICE")
public interface RatingService {

    @GetMapping("ratings/users/{userId}")
     List<Rating> getRatings(@PathVariable String userId);

    @PostMapping("/ratings")
    Rating addRating(@RequestBody Rating rating);

}
