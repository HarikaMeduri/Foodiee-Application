package com.niit.Userdata.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Document
public class Customer {
    @Transient
    public static final String SEQUENCE_NAME="user_sequence";

    @Id
    private int userid;
    private String username;
    private String email;
    private String password;
    private long phonenumber;
    private String status;

    @JsonDeserialize(using=CustomStringDeserializer.class)
    private List<Address> addressList;

    private List<Cart> cartList;
    private List<Orders> ordersList;



}
