package com.niit.RestaurantService.repository;

import com.niit.RestaurantService.domain.Foods;
import com.niit.RestaurantService.domain.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant,Integer> {

    @Query("{'email':{$in:[?0]}}")
    Restaurant findByEmail(String email);

    @Query("{'email':{$in:[?0]}}")
    Restaurant deleteByEmail(String email);

//    @Query("{'city':{$in:[?0]}}")
//    Restaurant findAllRestaurantCity(String city);
//
//    @Query("{'city': { $all: ?0 } }")
//    List<Restaurant> findByCity(List<Restaurant> city);

    @Query("{'addressList.city':?0}")
    List<Restaurant> findByCity(String city);

    @Query("{'foodsList.foodname':?0}")
    List<Foods> findByFoodname(String foodname);

    @Query("select r From Restaurant r WHERE r.foodname =:r")
    boolean deleteFoodByFoodname(@Param("r")String foodname);



}
