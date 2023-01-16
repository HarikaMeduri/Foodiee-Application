package com.niit.AuthenticationService.controller;

import com.niit.AuthenticationService.domain.Userdetails;
import com.niit.AuthenticationService.exception.UserAlreadyExistExpection;
import com.niit.AuthenticationService.exception.UserNotFoundException;
import com.niit.AuthenticationService.service.SecurityTokenGenerator;
import com.niit.AuthenticationService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    private ResponseEntity<?> responseEntity;
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


//  User registration.
    @PostMapping("/register")
    public ResponseEntity<?> saveFuntion1(@RequestBody Userdetails userDetails) throws UserAlreadyExistExpection {

        try {
            return new ResponseEntity<>(userService.saveUser(userDetails), HttpStatus.CREATED);
        }
        catch (UserAlreadyExistExpection e){
            throw  new UserAlreadyExistExpection();
        }
    }




   // Retreiving details of all users.
    @GetMapping("/users/getalldetails")
    public ResponseEntity<?> getFuntion1(){
        return new ResponseEntity<>(userService.getAllDetails(), HttpStatus.OK);
    }



   // User Login
//    @PostMapping("/login")
//    public ResponseEntity<?> loginFunction1(@RequestBody Userdetails user) throws UserNotFoundException {
//        //return new ResponseEntity<>(userService.retreiveusernameandpassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
//
//        Map<String, String> map = null;
//        try {
//            Userdetails userObj = userService.retrieveByEmailAndPassword(user.getEmail(), user.getPassword());
//            if (userObj.getEmail().equals(user.getEmail())) {
//                map = securityTokenGenerator.generateToken(user);
//            }
//            responseEntity = new ResponseEntity(map, HttpStatus.OK);
//        }
//        catch(UserNotFoundException e){
//            throw new UserNotFoundException();
//        }
//        catch (Exception e){
//            responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }

    @PostMapping("/login")
    public ResponseEntity<?> loginFunction1(@RequestBody Userdetails user) throws UserNotFoundException {
        //return new ResponseEntity<>(userService.retreiveusernameandpassword(user.getUsername(), user.getPassword()), HttpStatus.OK);

        Map<String, String> map = null;
        try {
            Userdetails userObj = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
            if (userObj.getEmail().equals(user.getEmail())) {
                map = securityTokenGenerator.generateToken(user);
            }
            responseEntity = new ResponseEntity(map, HttpStatus.OK);
        }
        catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }
        catch (Exception e){
            responseEntity = new ResponseEntity("Try after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
