package com.niit.RestaurantService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Restaurant with specified id is not found")
public class RestaurantNotFoundException extends Exception {
}
