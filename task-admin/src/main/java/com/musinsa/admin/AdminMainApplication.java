package com.musinsa.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.musinsa.core", "com.musinsa.admin"})
public class AdminMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminMainApplication.class, args);
    }

}