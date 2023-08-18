package com.example.UserService.exceptions;
import lombok.*;
import com.example.UserService.services.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException exception){

        String msg=exception.getMessage();


      ApiResponse response= ApiResponse.builder().message(msg).success(true).status(HttpStatus.NOT_FOUND).build();

       return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
}
