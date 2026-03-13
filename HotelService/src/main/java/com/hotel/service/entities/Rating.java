package com.hotel.service.entities;

import com.hotel.service.payload.User;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private String ratingId;
    private String hotelId;
    private String userId;
    private int rating;
    private String feedback;
    private User user;
}
