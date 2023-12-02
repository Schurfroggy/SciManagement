package com.example.scimanagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.scimanagement.mapper")
public class SciManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SciManagementApplication.class, args);
    }

}
