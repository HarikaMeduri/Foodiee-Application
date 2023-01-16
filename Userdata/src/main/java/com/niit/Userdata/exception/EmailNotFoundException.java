package com.niit.Userdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "Email not Found")
public class EmailNotFoundException extends Exception{
}
