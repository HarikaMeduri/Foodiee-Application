package com.niit.Userdata.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
    @Transient
    public static final String SEQUENCE_NAME="cart_sequence";


    private String foodname;
    private double price;
    private int quantity=1;
    private double discountprice;
    private double subtotal;
    private String categoryname;
    private String subcategoryname;

}
