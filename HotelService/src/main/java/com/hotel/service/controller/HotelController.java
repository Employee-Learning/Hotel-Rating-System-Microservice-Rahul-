package com.hotel.service.controller;

import com.hotel.service.entities.Hotel;
import com.hotel.service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;


    @PostMapping
    public ResponseEntity<String> createHotel(@RequestBody Hotel hotel) {

        Hotel hotel1 = hotelService.createHotel(hotel);
        return new ResponseEntity<>(hotel1.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String hotelId) {
        return new ResponseEntity<>(hotelService.getHotelById(hotelId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<String> deleteHotelById(@PathVariable String hotelId) {
        hotelService.deleteById(hotelId);
        return ResponseEntity.ok("Hotel Entity is Deleted Successfully");

    }
}
