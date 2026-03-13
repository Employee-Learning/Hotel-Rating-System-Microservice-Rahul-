package com.hotel.service.external.services;

import com.hotel.service.config.FeignConfig;
import com.hotel.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "RATINGSERVICE", configuration = FeignConfig.class)
public interface RatingService {
    @GetMapping("/ratings/hotels/{hotelId}")
    List<Rating> getRatingsByHotelId(@PathVariable String hotelId);
}



