package com.lcwd.hotel.controller;

import com.lcwd.hotel.entities.Hotel;
import com.lcwd.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    //create

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){

        String hotelId= UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.create(hotel));
    }

    // get single

    @GetMapping ("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){

        return ResponseEntity.status(HttpStatus.OK).body(hotelService.get(hotelId));
    }

    // get all

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){

        return ResponseEntity.ok(hotelService.getAll());
    }


}
