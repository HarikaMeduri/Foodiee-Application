package com.niit.Userdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "cart_sequence")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartSequence {

    @Id
    private String id;
    private int cartseq;
}
