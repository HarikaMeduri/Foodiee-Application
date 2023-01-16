package com.niit.Userdata.repository;

import com.niit.Userdata.domain.Cart;
import com.niit.Userdata.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,Integer> {
    @Query("{'email':{$in:[?0]}}")
    Customer findByEmail(String email);

    @Query("{'cartList.foodname':?0}")
    Cart findFirstByFoodname(String foodname);


}
