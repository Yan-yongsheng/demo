package com.demo.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.demo.shop.mapper")
public class GraduateApplication {
    public static void main(String[] args) {
        SpringApplication.run(GraduateApplication.class, args);
    }
}
