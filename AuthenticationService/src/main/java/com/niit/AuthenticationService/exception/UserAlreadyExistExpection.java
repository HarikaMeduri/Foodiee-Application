package com.niit.AuthenticationService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "Email Id Already Registered")
public class UserAlreadyExistExpection extends Exception {
}
