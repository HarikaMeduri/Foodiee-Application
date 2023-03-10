package com.niit.Userdata.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection= "db_sequence")
@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DbSequence {

    @Id
    private String id;
    private int seq;


}
