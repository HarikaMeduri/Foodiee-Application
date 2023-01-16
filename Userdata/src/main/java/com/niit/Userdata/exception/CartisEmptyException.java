package com.niit.Userdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND, reason = "There are no items in the cart to delete")
public class CartisEmptyException extends Exception{
}
