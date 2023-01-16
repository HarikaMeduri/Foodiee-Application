package com.niit.AuthenticationService.service;

import com.niit.AuthenticationService.domain.Userdetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(Userdetails userdetails) {
        String jwtToken;// = null;
        jwtToken = Jwts.builder().setSubject(userdetails.getEmail()).setIssuedAt(new Date())
                .signWith(  SignatureAlgorithm.HS256,"secretkey").compact();
        Map<String, String> map = new HashMap<>();
        System.out.println(jwtToken);
        map.put("token", jwtToken);
        map.put("message", "User Login Successful");
        System.out.println(jwtToken);
        return map;
    }
}
