package com.user.service.entities;

import jakarta.persistence.Transient;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private String userId;
    private String hotelId;
    private String ratingId;
    private int rating;
    private String feedback;
    private Hotel hotel;
}
