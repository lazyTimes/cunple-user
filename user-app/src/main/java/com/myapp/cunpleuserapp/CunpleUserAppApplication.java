package com.myapp.cunpleuserapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myapp.cunpleuserapp.mapper")
public class CunpleUserAppApplication {



    public static void main(String[] args) {
        SpringApplication.run(CunpleUserAppApplication.class, args);
    }

}
