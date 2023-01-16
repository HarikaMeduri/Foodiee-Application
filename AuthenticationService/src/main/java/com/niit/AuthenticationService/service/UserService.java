package com.niit.AuthenticationService.service;

import com.niit.AuthenticationService.domain.Userdetails;
import com.niit.AuthenticationService.exception.UserAlreadyExistExpection;
import com.niit.AuthenticationService.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    Userdetails saveUser(Userdetails userDetails) throws UserAlreadyExistExpection;
    List<Userdetails> getAllDetails();
    Userdetails retrieveByEmail(String email);
    Userdetails retrieveByEmailAndPassword(String email, String password) throws UserNotFoundException;

    Userdetails findByEmailAndPassword(String email, String password) throws UserNotFoundException;
    Userdetails findByEmail(String email);
}
