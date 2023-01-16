package com.niit.AuthenticationService.config;

import com.niit.AuthenticationService.domain.Userdetails;
import com.niit.AuthenticationService.exception.UserAlreadyExistExpection;
import com.niit.AuthenticationService.repository.UserRepository;
import com.niit.AuthenticationService.service.UserServiceImpl;
import com.niit.rabbitmq.domain.RestaurantDTO;
import com.niit.rabbitmq.domain.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

//    Userdetails userDetails=new Userdetails();

    @RabbitListener(queues = "customer_queue")        //A Handler object accepts a logging request and exports the desired messages to a target
    public void getDatafromRabbitMQserver(UserDTO userDTO) throws UserAlreadyExistExpection {
        Userdetails userDetails=new Userdetails();

        userDetails.setUserid(userDTO.getUserid());
        userDetails.setEmail(userDTO.getEmail());
        userDetails.setPassword(userDTO.getPassword());
        userDetails.setStatus(userDTO.getStatus());
        System.out.println(""+userDetails);

        userService.saveUser(userDetails);
    }
    @RabbitListener(queues = "restaurant_queue")
    public void getDatafromRabbitMQserverrestaurant(RestaurantDTO restaurantdto) throws UserAlreadyExistExpection {

        System.out.println("check email from rest:"+restaurantdto.getEmail());
        System.out.println(" from rest:"+restaurantdto);
        System.out.println(" from rest status:"+restaurantdto.getStatus());
        Userdetails userDetails1 = userRepository.findByEmail(restaurantdto.getEmail());
        System.out.println(" userdetails1 obj:"+userDetails1);
        userDetails1.setStatus(restaurantdto.getStatus());
        System.out.println("updated:::"+userDetails1);

        userService.saveUser(userDetails1);
    }


}
