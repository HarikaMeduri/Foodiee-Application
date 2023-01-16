package com.niit.rabbitmq.domain;

import com.niit.RestaurantService.domain.Address;
import com.niit.RestaurantService.domain.Foods;

import java.util.List;

public class RestaurantDTO {


    private int restaurantid;
    private String restaurantname;
    private String phonenumber;
    private String email;
    private String status;
    private List<Foods> foodsList;
    private List<Address> addressList;
    private String restaurantimage;

    public RestaurantDTO() {
    }

//    public RestaurantDTO(int restaurantid, String restaurantname, String phonenumber, String email, String status, List<Foods> foodsList, List<Address> addressList, String restaurantimage) {
//        this.restaurantid = restaurantid;
//        this.restaurantname = restaurantname;
//        this.phonenumber = phonenumber;
//        this.email = email;
//        this.status = status;
//        this.foodsList = foodsList;
//        this.addressList = addressList;
//        this.restaurantimage = restaurantimage;
//    }
//
//    public RestaurantDTO(String status) {
//        this.status = status;
//    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Foods> getFoodsList() {
        return foodsList;
    }

    public void setFoodsList(List<Foods> foodsList) {
        this.foodsList = foodsList;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public String getRestaurantimage() {
        return restaurantimage;
    }

    public void setRestaurantimage(String restaurantimage) {
        this.restaurantimage = restaurantimage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "restaurantid=" + restaurantid +
                ", restaurantname='" + restaurantname + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                ", foodsList=" + foodsList +
                ", addressList=" + addressList +
                ", restaurantimage='" + restaurantimage + '\'' +
                '}';
    }
}
