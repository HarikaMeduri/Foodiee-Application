package com.niit.Userdata.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Address {
    private int addid;
    private String dno;
    private String street;
    private String city;
    private String state;
    private int pincode;

}
