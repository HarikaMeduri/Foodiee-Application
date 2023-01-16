package com.niit.AuthenticationService.repository;

import com.niit.AuthenticationService.domain.Userdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Userdetails, Integer> {

    Userdetails findByEmail(String email);
    public Userdetails findByEmailAndPassword(String email, String password);

}
