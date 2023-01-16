package com.niit.RestaurantService.controller;

import com.niit.RestaurantService.domain.Address;
import com.niit.RestaurantService.domain.Foods;
import com.niit.RestaurantService.domain.ImageModel;
import com.niit.RestaurantService.domain.Restaurant;
import com.niit.RestaurantService.exception.FoodNotFoundException;
import com.niit.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.exception.RestaurantNotFoundException;
import com.niit.RestaurantService.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
public class RestaurantController {

    private ResponseEntity responseEntity;

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(final RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
    }

    //Register a Restaurant
    @PostMapping(value = {"api/v3/addRestaurant"}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> saveRestaurant(@RequestPart("restaurant") Restaurant restaurant,
                                            @RequestPart("imageFile") MultipartFile[] file) throws RestaurantAlreadyExistsException {
        try
        {
            Set<ImageModel> images = uploadImage(file);
            restaurant.setRestaurantimage(images);
            restaurantService.saveRestaurantDetails(restaurant);
            responseEntity = new ResponseEntity(restaurant , HttpStatus.CREATED);
        }
        catch (RestaurantAlreadyExistsException e)
        {
            throw new RestaurantAlreadyExistsException();
        }
        catch (Exception e)
        {
            responseEntity = new ResponseEntity<>("Error !!!Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModelSet = new HashSet<>();
        for(MultipartFile file:multipartFiles)
        {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModelSet.add(imageModel);
        }
        return imageModelSet;
    }

    //Delete a Restaurant by Id
    @DeleteMapping("api/v3/deleteRestaurant/{restaurantid}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable("restaurantid") int restaurantid) throws RestaurantNotFoundException {
        try {
            restaurantService.deleteRestaurantById(restaurantid);
            responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            throw new RestaurantNotFoundException();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            responseEntity = new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Delete a Restaurant by Email
    @DeleteMapping("api/v3/deleteRestaurantByEmail/{email}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable("email") String email) throws RestaurantNotFoundException {
        restaurantService.deleteRestaurantByEmail(email);
        responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.OK);
        return responseEntity;
    }

    //View all Restaurant
    @GetMapping("api/v3/getRestaurant")
    public ResponseEntity<?> getAllRestaurant(){
        try{
            responseEntity = new ResponseEntity(restaurantService.getAllRestaurantDetails(), HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error !!! Try after sometime ."+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //View Perticular Restaurant with email
    @GetMapping("api/v3/getSingleRestaurant/{email}")
    public ResponseEntity<?> getSingleRestaurant(@PathVariable String email){
        try{
            responseEntity = new ResponseEntity(restaurantService.getSingleRestaurant(email), HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error !!! Try after sometime ."+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Search Restaurants by city name
    @GetMapping("api/v3/searchRestaurantsByCity/{city}")
    public ResponseEntity<?> getRestaurantByCityName(@PathVariable String city) throws RestaurantNotFoundException {
        try{
             responseEntity = new ResponseEntity(restaurantService.searchRestaurantsByCity(city), HttpStatus.OK);
        }
        catch (RestaurantNotFoundException e)
        {
            responseEntity = new ResponseEntity("Error !!! Try after sometime ."+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }



    //Update Foodlist in a Restaurant
//    @PutMapping("api/v3/updateFoodlist/{email}")
//    public ResponseEntity<?> updateFoodlistOfRestaurant(@PathVariable String email, @RequestBody Foods foods) throws RestaurantNotFoundException {
//        return new ResponseEntity<>(restaurantService.updateRestaurantFoodListByEmail(email,foods),HttpStatus.ACCEPTED);
//    }

    //Update Foodlist in a Restaurant
    @PutMapping( value = {"api/v3/updateFoodlist/{email}"}, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateFoodlistOfRestaurant(@RequestPart("foods") Foods foods,
                                                        @PathVariable String email,
                                                        @RequestPart("imageFile") MultipartFile[] file) throws RestaurantNotFoundException {

        try{
            Set<ImageModel> images = uploadImage(file);
            foods.setFoodimage(images);
            restaurantService.updateRestaurantFoodListByEmail(email,foods);
            responseEntity = new ResponseEntity(foods, HttpStatus.ACCEPTED);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseEntity;
    }




    //Delete food item from a Restaurant
    @DeleteMapping("api/v3/deleteFoodItem/{restaurantid}{foodid}")
    public ResponseEntity<?> deleteFoodFromRestaurants(@PathVariable("restaurantid") int restaurantid, @PathVariable("foodid") int foodid) throws RestaurantNotFoundException, FoodNotFoundException {
        try {
            restaurantService.deleteRestaurantFoodItemById(restaurantid, foodid);
            responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.OK);
        } catch (RestaurantNotFoundException e) {
            throw new RestaurantNotFoundException();
        }  catch (FoodNotFoundException ex) {
            throw new FoodNotFoundException();
        } catch (Exception exception) {
//            String ex = exception.getMessage();
            System.out.println(exception.getMessage());
            responseEntity = new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //Delete Food item using name
    @DeleteMapping("api/v3/deleteFoodItem/{email}/{foodname}")
    public ResponseEntity<?> deleteFoodFromRestaurants(@PathVariable String email,@PathVariable String foodname) throws FoodNotFoundException {
        restaurantService.deleteRestaurantFoodItemByName(email,foodname);
        responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.OK);
        return responseEntity;
    }

    //View Foodlist of a restaurants
    @GetMapping("api/v3/getFoodList/{email}")
    public ResponseEntity<?> getAllFoodsOfaRestaurants(@PathVariable String email) throws FoodNotFoundException {
        try{
            responseEntity = new ResponseEntity(restaurantService.getFoodlistByEmail(email), HttpStatus.OK);
        } catch (FoodNotFoundException e){
            throw new FoodNotFoundException();
        } catch (Exception e){
            responseEntity = new ResponseEntity("Error !!! Try after sometime ."+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //Search Foods by food name
    @GetMapping("api/v3/searchFoodByName/{email}/{foodname}")
    public ResponseEntity<?> getFoodByFoodName(@PathVariable String email, @PathVariable String foodname) throws FoodNotFoundException {
        try{
            responseEntity = new ResponseEntity(restaurantService.searchFoodByFoodName(email,foodname), HttpStatus.OK);
        }
        catch (FoodNotFoundException e)
        {
            responseEntity = new ResponseEntity("Error !!! Try after sometime ."+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //Get All Fooditems from All Restaurants
    @GetMapping("api/v3/getAllFooditemsFromAllRestaurant")
    public ResponseEntity<?> getAllFooditems(){
        try{
            responseEntity = new ResponseEntity(restaurantService.getAllFoodItemsFromAllRestaurant(), HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity("Error !!! Try after sometime ."+e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //Update Restaurant Address
//    @PutMapping("api/v3/updateAddresslist/{email}")
//    public ResponseEntity<?> updateAddresslist(@PathVariable String email, @RequestBody Address address) throws RestaurantNotFoundException
//    {
//        return new ResponseEntity<>(restaurantService.updateRestaurantAddressListByEmail(email,address),HttpStatus.ACCEPTED);
//    }


}
