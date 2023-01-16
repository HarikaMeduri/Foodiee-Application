package com.niit.AuthenticationService.service;

import com.niit.AuthenticationService.domain.Userdetails;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String, String> generateToken(Userdetails userdetails);
}
