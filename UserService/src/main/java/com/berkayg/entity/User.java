package com.berkayg.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document  //MongoDB için
public class User {
    @MongoId
    String id;
    Long authId;
    String username;
    String email;
    String photo;
    String phone;
    String website;
    String about;
}
