package com.lcwd.rating.repositories;

import com.lcwd.rating.entites.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface RatingRepo extends JpaRepository<Rating,String> {

    //custom finder methods

    List<Rating> findByUserId(String userId);

    List<Rating> findByHotelId(String hotelId);




}
