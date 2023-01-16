package com.niit.Userdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Orders {
    @Id
    private int orderid;
    private String foodname;
    private String date;
    private int quantity;
    private double price;

}
