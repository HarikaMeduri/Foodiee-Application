package com.niit.RestaurantService.service;

import com.niit.RestaurantService.config.Producer;
import com.niit.RestaurantService.domain.Address;
import com.niit.RestaurantService.domain.DatabaseSequence;
import com.niit.RestaurantService.domain.Foods;
import com.niit.RestaurantService.domain.Restaurant;
import com.niit.RestaurantService.exception.FoodNotFoundException;
import com.niit.RestaurantService.exception.RestaurantAlreadyExistsException;
import com.niit.RestaurantService.exception.RestaurantNotFoundException;
import com.niit.RestaurantService.repository.FoodRepository;
import com.niit.RestaurantService.repository.RestaurantRepository;
import com.niit.rabbitmq.domain.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import org.springframework.data.mongodb.core.query.Update;

import static java.util.function.Predicate.not;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private FoodRepository foodRepository;
    private RestaurantRepository restaurantRepository;

    private Foods foods;

    private Address address;

    @Autowired
    private Producer producer;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository,
                                 MongoOperations mongoOperations,
                                 FoodRepository foodRepository)
    {
        this.restaurantRepository = restaurantRepository;
        this.mongoOperations = mongoOperations;
        this.foodRepository = foodRepository;
    }

    //To generate auto id in mongodb autowiring and a method
    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public int generateSequence(String seqName) {
        Query query = new Query(Criteria.where("id").is(seqName));
        Update update = new Update().inc("seq",1);
        DatabaseSequence counter = mongoOperations.findAndModify(query,update,options().returnNew(true).upsert(true),DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    //Register Restaurant Details
    @Override
    public Restaurant saveRestaurantDetails(Restaurant restaurant) throws RestaurantAlreadyExistsException, IOException {
        Restaurant restaurant1;
        Optional<Restaurant> restaurantOptional= Optional.ofNullable(restaurantRepository.findByEmail(restaurant.getEmail()));
        if(restaurantOptional.isPresent())
        {
            throw new RestaurantAlreadyExistsException();
        }
        else {
            restaurant.setRestaurantid(generateSequence(Restaurant.SEQUENCE_NAME));
//           restaurant.setStatus("yes");
            restaurant1 = restaurantRepository.save(restaurant);
            System.out.println("Restaurants Details"+restaurant1);
        }
        RestaurantDTO restaurantDTO = new RestaurantDTO();
//        if(restaurantDTO.getEmail()==restaurant.getEmail()){
            restaurantDTO.setEmail(restaurant.getEmail());
            restaurantDTO.setStatus(restaurant.getStatus());
//        }
        producer.sendMessageToRabbitMq1(restaurantDTO);
        System.out.println("Helloo"+producer);
        return restaurant;


//        restaurantDTO.setCustomername(restaurant.getRestaurantname());
//        restaurantDTO.setShipaddress(restaurant.getPhonenumber());
//        restaurantDTO.setEmail(restaurant.getEmail());
//        restaurantDTO.setPassword(restaurant.getAddress());
//        restaurantDTO.setProductList(restaurant.getFoodsList());
//
//        producer.sendMessageToRabbitMq(restaurantDTO);
    }

    //Delete Restaurant By Email
    @Override
    public boolean deleteRestaurantByEmail(String email) throws RestaurantNotFoundException {
        boolean flag =false;
        int rid = restaurantRepository.findByEmail(email).getRestaurantid();
        if(rid==0)
        {
            throw new RestaurantNotFoundException();
        }
        else {
            restaurantRepository.deleteById(rid);
            System.out.println("by email");
            flag = true;
        }
        return flag;
    }

    //Delete Restaurant By Id
    @Override
    public boolean deleteRestaurantById(int restaurantsid) throws RestaurantNotFoundException {
        boolean flag = false;
        if(restaurantRepository.findById(restaurantsid).isEmpty())
        {
            throw new RestaurantNotFoundException();
        }
        else {
            restaurantRepository.deleteById(restaurantsid);
            flag = true;
        }
        return false;
    }

    //Get all Restaurant Details
    @Override
    public List<Restaurant> getAllRestaurantDetails() throws Exception {
        return restaurantRepository.findAll();
    }


    //Get a Perticular Restaurants Details
    @Override
    public Restaurant getSingleRestaurant(String email) throws Exception {
        Restaurant restaurant2 = restaurantRepository.findByEmail(email);
        System.out.println(restaurant2);
        if(restaurant2==null)
        {
            throw new RestaurantNotFoundException();
        }
        else {
            return restaurantRepository.findByEmail(email);
        }
    }

    //Search Restaurants by city name
    @Override
    public List<Restaurant> searchRestaurantsByCity(String city) throws RestaurantNotFoundException
    {
        List<Restaurant> restaurant2 = restaurantRepository.findByCity(city);
        if(restaurant2.isEmpty())
        {
            throw new RestaurantNotFoundException();
        }
        else {
            System.out.println("Search Results :"+restaurant2);
            return restaurant2;
        }
    }


    //Update Restaurants Food Details
    @Override
    public boolean updateRestaurantFoodListByEmail(String email, Foods foods) throws RestaurantNotFoundException {
        Restaurant restaurant1 = restaurantRepository.findByEmail(email);
        System.out.println(restaurant1);
//        Product product1 = new Product();
//        Furniture furniture1 = new Furniture();
//
//        product1.setProductcode(furniture1.getFurniturecode());
//        product1.setProductName(furniture1.getFurniturename());
//        product1.setProductdescription(furniture1.getFurnituredescription());
//        product1.setIsinstock(furniture1.getIsinstock());
//        product1.setProductcolor(furniture1.getFurniturecolor());
//        product1.setProductsize(furniture1.getFurnituresize());
//        product1.setProductprice(furniture1.getFurnitureprice());
//        Product convertedObj = this.modelMapper.map(product, Product.class);
        boolean flag = false;
        if(restaurant1==null)
        {
            throw new RestaurantNotFoundException();
        }
        if(restaurant1.getFoodsList()==null)
        {
            System.out.println(restaurant1);
            restaurant1.setFoodsList(Arrays.asList(foods));
            System.out.println(foods);
            System.out.println(restaurant1);
            restaurantRepository.save(restaurant1);
            flag=true;
        }
        else
        {
            List<Foods> foodsList = restaurant1.getFoodsList();
            foodsList.add(foods);
            restaurant1.setFoodsList(foodsList);
            restaurantRepository.save(restaurant1);
            flag=true;
        }
        return flag;
    }

    //Delete Perticular Food item by Id from a Restaurant By Id [NOT COMPLETED]
    @Override
    public boolean deleteRestaurantFoodItemById(int restaurantid, int foodid) throws FoodNotFoundException ,RestaurantNotFoundException{
        boolean flag = false;
        if(restaurantRepository.findById(restaurantid).isEmpty())
        {
            throw new RestaurantNotFoundException();
        }
        else if (restaurantRepository.findById(foodid).isEmpty())
        {
            throw new FoodNotFoundException();
        }
        else
        {
            restaurantRepository.deleteById(foodid);
            flag = true;
        }
        return flag;
    }

    @Override
    //Delete Particular food item by food name
    public boolean deleteRestaurantFoodItemByName(String email,String foodname) throws FoodNotFoundException{
        boolean flag = false;
        Restaurant restaurant2 = restaurantRepository.findByEmail(email);
        List<Foods> newList1 = restaurant2.getFoodsList();
        System.out.println("Before filter"+newList1);

        if(newList1.isEmpty())
        {
            throw new FoodNotFoundException();
        }
        else {
            newList1 = newList1.stream().filter(not(s->s.getFoodname().equalsIgnoreCase(foodname))).collect(Collectors.toList());
            System.out.println("After filter :"+newList1);
            restaurant2.setFoodsList(newList1);
            System.out.println("After 2 filter :"+newList1);
            restaurantRepository.save(restaurant2);
            flag = true;
        }
        return flag;
    }

    //Get Food-list of a Restaurant
    @Override
    public List<Foods> getFoodlistByEmail(String email)
    {
        List<Foods> foodsList = restaurantRepository.findByEmail(email).getFoodsList();
        System.out.println("Foodlist through email"+foodsList);
        return foodsList;
    }

    //Search Food by food name
    @Override
    public List<Foods> searchFoodByFoodName(String email,String foodname) throws FoodNotFoundException
    {
        List<Foods> foodsList1;
        foodsList1 = restaurantRepository.findByEmail(email).getFoodsList();
        System.out.println("Foodlist before filter:"+foodsList1);
        if(foodsList1.isEmpty())
        {
            throw new FoodNotFoundException();
        }
        else {
            foodsList1 = foodsList1.stream().filter(s->s.getFoodname().equalsIgnoreCase(foodname)).collect(Collectors.toList());
            System.out.println("Search Results after filter :"+foodsList1);
            return foodsList1;
        }
    }

    //Get all Foods From All Restaurants
    @Override
    public List<Restaurant> getAllFoodItemsFromAllRestaurant() throws Exception {

            List<Restaurant> restaurantList = restaurantRepository.findAll();
//            List<Foods> foodsList1 = restaurantList.stream().filter(s->s.getFoodsList()).collect(Collectors.toList());
            restaurantList.get(0).getFoodsList();
        System.out.println(restaurantList);
            return restaurantList;
        }


    //Update Restaurants Address Details
//    @Override
//    public boolean updateRestaurantAddressListByEmail(String email, Address address) throws RestaurantNotFoundException {
//        Restaurant restaurant1 = restaurantRepository.findByEmail(email);
//        System.out.println(restaurant1);
//        boolean flag = false;
//        if(restaurant1==null)
//        {
//            throw new RestaurantNotFoundException();
//        }
//        if(restaurant1.getAddressList()==null)
//        {
//            System.out.println(restaurant1);
//            restaurant1.setAddressList(Arrays.asList(address));
//            System.out.println(address);
//            System.out.println(restaurant1);
//            restaurantRepository.save(restaurant1);
//            flag=true;
//        }
//        else
//        {
//            List<Address> addressList = restaurant1.getAddressList();
//            addressList.add(address);
//            restaurant1.setAddressList(addressList);
//            restaurantRepository.save(restaurant1);
//            flag=true;
//        }
//        return flag;
//    }
}
