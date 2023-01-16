package com.niit.AuthenticationService.service;

import com.niit.AuthenticationService.domain.Userdetails;
import com.niit.AuthenticationService.exception.UserAlreadyExistExpection;
import com.niit.AuthenticationService.exception.UserNotFoundException;
import com.niit.AuthenticationService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




//    @Override
//    public Userdetails saveUser(Userdetails userDetails)throws UserAlreadyExistExpection {
//        Userdetails user1 = userRepository.findByEmail(userDetails.getEmail());
//        if(user1 == null){
//            return userRepository.save(userDetails);
//        }
//        else if(user1.getEmail().equals(userDetails.getEmail())) {
//            throw new UserAlreadyExistExpection();
//        }
//        else
//            return userRepository.save(userDetails);
//    }

    @Override
    public Userdetails saveUser(Userdetails userDetails)throws UserAlreadyExistExpection{
//        Userdetails user1 = userRepository.findByEmail(userDetails.getEmail());
//        if(user1.getEmail().equals(userDetails.getEmail())) {
//            throw new UserAlreadyExistExpection();
//        }
//        else
            return userRepository.save(userDetails);
    }

    @Override
    public List<Userdetails> getAllDetails() {
        return (List<Userdetails>) userRepository.findAll();
    }

    @Override
    public Userdetails retrieveByEmail(String email) {
        return (Userdetails) userRepository.findByEmail(email);
    }


    @Override
    public Userdetails retrieveByEmailAndPassword(String email, String password) throws UserNotFoundException {
        Userdetails user =userRepository.findByEmailAndPassword(email, password);
        if(user == null)
            throw new UserNotFoundException();
        else
            return (Userdetails) userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Userdetails findByEmailAndPassword(String email, String password) throws UserNotFoundException {
        Userdetails user =userRepository.findByEmailAndPassword(email, password);
        if(user == null)
            throw new UserNotFoundException();
        else
            return user;
    }

    @Override
    public Userdetails findByEmail(String email) {
        Userdetails user1=userRepository.findByEmail(email);
        return user1;
    }


}
