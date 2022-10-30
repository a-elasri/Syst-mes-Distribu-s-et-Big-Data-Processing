package com.example.activitypratique.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity@AllArgsConstructor
@NoArgsConstructor @Data
public class Customer {
    @Id
    private String id;
    private  String name;
    private String email;
}
