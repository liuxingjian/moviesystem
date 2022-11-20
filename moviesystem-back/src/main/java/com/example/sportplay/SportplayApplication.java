package com.example.sportplay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.sportplay.dao")
@SpringBootApplication
public class SportplayApplication {
    public static void main(String[] args) {
        SpringApplication.run(SportplayApplication.class, args);
    }

}
