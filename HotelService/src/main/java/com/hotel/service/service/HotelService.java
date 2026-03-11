package com.hotel.service.service;

import com.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    //create hotel
    Hotel createHotel(Hotel hotel);

    //get one hotel
    Hotel getHotelById(String hotelId);

    //getAllHotel
    List<Hotel> getAllHotels();

    void deleteById(String hotelId);
}
