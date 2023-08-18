package com.lcwd.rating.Service;

import com.lcwd.rating.entites.Rating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RatingService {

    //create
    Rating createRating(Rating rating);

    //get all ratings

    List<Rating> getRatings();

    //get all by UserID

    List<Rating> getRatingByUserId(String userId);

    //get all by hotel

    List<Rating> getRatingByHotelId(String hotelId);


}
