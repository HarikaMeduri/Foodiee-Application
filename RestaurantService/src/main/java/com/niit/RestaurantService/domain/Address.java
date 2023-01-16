package com.niit.RestaurantService.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    @Id
    private int buildingnumber;
    private String street;
    private String city;
    private int pincode;
    private String state;
    private String country;
}
