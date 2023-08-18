package com.example.UserService.services.impl;

import com.example.UserService.entities.Hotel;
import com.example.UserService.entities.Rating;
import com.example.UserService.entities.User;
import com.example.UserService.exceptions.ResourceNotFoundException;
import com.example.UserService.externalServices.HotelService;
import com.example.UserService.repositories.UserRepository;
import com.example.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private HotelService hotelService;
    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + userId));
        //fetch rating of user from user service
        //http://localhost:8083/ratings/users/9d2ba472-9c6c-4253-aaa4-0a6795f04334


        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);        logger.info("{}", ratingsOfUser);

        // Now convert this array of List into ArrayList to get rid from LinkedHashMap cannot be cast

        List<Rating> ratings= Arrays.stream(ratingsOfUser).toList();

        //Now insert hotels in rating one by one

        List<Rating> ratinglist = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //http://localhost:8082/hotels/b15c04b9-8b62-4b55-95ef-0c120c96ecf8
            // ResponseEntity<Hotel> forEntity =restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+ rating.getHotelId(), Hotel.class);
            //Hotel hotel = forEntity.getBody();
            //logger.info("response status code:{}",forEntity.getStatusCode());
            Hotel hotel= hotelService.getHotel(rating.getHotelId());
            // set the hotel to rating
            rating.setHotel(hotel);
            // return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRating(ratinglist);
        return user;


    }
}
