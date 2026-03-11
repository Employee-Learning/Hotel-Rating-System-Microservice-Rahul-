package com.hotel.service.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "hotel")
@Data
public class Hotel {
    @Id
    private String hotelId;

    @Column(name = "hotelName")
    private String hotelName;

    @Column(name = "location")
    private String location;

    @Column(name = "about")
    private String about;
}
