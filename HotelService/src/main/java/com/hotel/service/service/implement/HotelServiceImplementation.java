package com.hotel.service.service.implement;

import com.hotel.service.entities.Hotel;
import com.hotel.service.exception.ResourceNotFoundException;
import com.hotel.service.repository.HotelRepository;
import com.hotel.service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelServiceImplementation implements HotelService {

    private final HotelRepository  hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String uuid = UUID.randomUUID().toString();
        hotel.setHotelId(uuid);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotelById(String hotelId) {
        Hotel hotel= hotelRepository.findById(hotelId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Hotel with this id is not present in the database " + hotelId));
        return hotel;
    }


    @Override
    public List<Hotel> getAllHotels() {
       return hotelRepository.findAll();
    }


    @Override
    public void deleteById(String hotelId) {

        if (!hotelRepository.existsById(hotelId)) {
            throw new ResourceNotFoundException("Hotel not found with id: " + hotelId);
        }
        hotelRepository.deleteById(hotelId);
    }
}
