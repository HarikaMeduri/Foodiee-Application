package com.niit.RestaurantService.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "database_sequences")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DatabaseSequence {

    @Id
    private String id;
    private int seq;
}