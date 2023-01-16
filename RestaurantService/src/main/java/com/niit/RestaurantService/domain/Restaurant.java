package com.niit.RestaurantService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurant {
    @Transient
    public static final String SEQUENCE_NAME = "restaurants_sequence";

    @Id
    private int restaurantid;
    private String restaurantname;
    private String phonenumber;
    private String email;
    private String status;
    private List<Foods> foodsList;
    private int buildingnumber;
    private String street;
    private String city;
    private int pincode;
    private String state;
    private String country;
    private Set<ImageModel> restaurantimage;
}

//    public Restaurant() {
//    }
//
//    public Restaurant(String restaurantname, String phonenumber, String email, List<Foods> foodsList, List<Address> addressList, String restaurantimage) {
//        this.restaurantname = restaurantname;
//        this.phonenumber = phonenumber;
//        this.email = email;
//        this.foodsList = foodsList;
//        this.addressList = addressList;
//        this.restaurantimage = restaurantimage;
//    }
//
//    @Override
//    public String toString() {
//        return "Restaurant{" +
//                "restaurantid=" + restaurantid +
//                ", restaurantname='" + restaurantname + '\'' +
//                ", phonenumber='" + phonenumber + '\'' +
//                ", email='" + email + '\'' +
//                ", foodsList=" + foodsList +
//                ", addressList=" + addressList +
//                ", restaurantimage='" + restaurantimage + '\'' +
//                '}';
//    }
//
//    public int getRestaurantid() {
//        return restaurantid;
//    }
//
//    public void setRestaurantid(int restaurantid) {
//        this.restaurantid = restaurantid;
//    }
//
//    public String getRestaurantname() {
//        return restaurantname;
//    }
//
//    public void setRestaurantname(String restaurantname) {
//        this.restaurantname = restaurantname;
//    }
//
//    public String getPhonenumber() {
//        return phonenumber;
//    }
//
//    public void setPhonenumber(String phonenumber) {
//        this.phonenumber = phonenumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public List<Foods> getFoodsList() {
//        return foodsList;
//    }
//
//    public void setFoodsList(List<Foods> foodsList) {
//        this.foodsList = foodsList;
//    }
//
//    public List<Address> getAddressList() {
//        return addressList;
//    }
//
//    public void setAddressList(List<Address> addressList) {
//        this.addressList = addressList;
//    }
//
//    public String getRestaurantimage() {
//        return restaurantimage;
//    }
//
//    public void setRestaurantimage(String restaurantimage) {
//        this.restaurantimage = restaurantimage;
//    }
//}
