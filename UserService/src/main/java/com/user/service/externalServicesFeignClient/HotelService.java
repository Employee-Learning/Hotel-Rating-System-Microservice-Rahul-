package com.user.service.externalServicesFeignClient;

import com.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "HOTELSERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);

    @PostMapping("/hotels")
    Hotel addHotel(@RequestBody Hotel hotel);

    @DeleteMapping("/hotels/{hotelId}")
    Void deleteHotel(@PathVariable String hotelId);

    @PutMapping("/hotels/{hotelId}")
    Hotel  updateHotel(@PathVariable String hotelId, @RequestBody Hotel hotel);
}




