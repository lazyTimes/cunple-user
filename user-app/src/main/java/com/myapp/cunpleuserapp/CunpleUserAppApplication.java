package com.myapp.cunpleuserapp;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myapp.cunpleuserapp.mapper")
@EnableDubboConfig
@DubboComponentScan("com.myapp.cunpleuserapp.service")
public class CunpleUserAppApplication {



    public static void main(String[] args) {
        SpringApplication.run(CunpleUserAppApplication.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("jvm 线程关闭");
            }
        }));
    }

}
