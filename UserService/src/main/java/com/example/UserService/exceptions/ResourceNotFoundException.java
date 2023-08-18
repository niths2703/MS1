package com.example.UserService.exceptions;

public class ResourceNotFoundException extends RuntimeException{


    //extra properties to be managed
    public ResourceNotFoundException(){
        super("Resource not found on server !!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
