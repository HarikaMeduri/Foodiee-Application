package com.niit.RestaurantService.repository;

import com.niit.RestaurantService.domain.Foods;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Foods,Integer> {

}
