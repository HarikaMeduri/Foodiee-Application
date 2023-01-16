package com.niit.Userdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "User with specified emailid is already exists,try different email")
public class EmailAlreadyExistsException extends Exception{
}
