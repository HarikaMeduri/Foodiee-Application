package com.niit.RestaurantService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT,reason = "Restaurant with specified id already exists")
public class RestaurantAlreadyExistsException extends Exception{

}
