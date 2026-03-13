package com.user.service.entities;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private String hotelId;
    private String hotelName;
    private String location;
    private String about;
    @Transient
    private Double averageRating;
}

