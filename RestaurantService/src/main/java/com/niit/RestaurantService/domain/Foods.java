package com.niit.RestaurantService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Foods {
    @Id
    private int foodid;
    private String foodname;
    private double price;
    private double discountprice;
    private String categoryname;
    private String subcategoryname;
    private String description;
    private Set<ImageModel> foodimage;


}
