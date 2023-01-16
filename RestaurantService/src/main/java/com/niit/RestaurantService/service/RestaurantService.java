package com.niit.RestaurantService.service;

import com.niit.RestaurantService.domain.Address;
import com.niit.RestaurantService.domain.Foods;
import com.niit.RestaurantService.domain.Restaurant;
import com.niit.RestaurantService.exception.FoodNotFoundException;
import com.niit.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.exception.RestaurantNotFoundException;

import java.io.IOException;
import java.util.List;

public interface RestaurantService {

    Restaurant saveRestaurantDetails(Restaurant restaurant) throws RestaurantAlreadyExistsException, IOException;
    List<Restaurant> getAllRestaurantDetails() throws Exception;
    Restaurant getSingleRestaurant(String email) throws Exception;
//    List<Restaurant> getSingleRestaurant(String email) throws Exception;
    boolean deleteRestaurantById(int restaurantsid) throws RestaurantNotFoundException;
    boolean deleteRestaurantByEmail(String email) throws RestaurantNotFoundException;
    List<Restaurant> searchRestaurantsByCity(String city) throws RestaurantNotFoundException;

    //To generate auto id
    int generateSequence(String seqName);

    boolean updateRestaurantFoodListByEmail(String email, Foods foods) throws RestaurantNotFoundException;
    List<Foods> getFoodlistByEmail(String email) throws FoodNotFoundException;
    boolean deleteRestaurantFoodItemById(int restaurantid, int foodid) throws Exception;
    boolean deleteRestaurantFoodItemByName(String email,String foodname) throws FoodNotFoundException;
    List<Foods> searchFoodByFoodName(String email, String foodname) throws FoodNotFoundException;

    List<Restaurant> getAllFoodItemsFromAllRestaurant() throws Exception;
//    boolean updateRestaurantAddressListByEmail(String email, Address address) throws RestaurantNotFoundException;
}
