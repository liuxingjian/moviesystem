package com.example.sportplay.springbootvue.bean;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class Book {

    @Id
    private Integer id;
    private String name;
    private Integer num;

}