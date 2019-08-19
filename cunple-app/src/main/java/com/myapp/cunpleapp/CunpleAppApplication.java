package com.myapp.cunpleapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myapp.cunpleapp.mapper")
public class CunpleAppApplication {



    public static void main(String[] args) {
        SpringApplication.run(CunpleAppApplication.class, args);
    }

}
