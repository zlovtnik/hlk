package com.climate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ClimateApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ClimateApplication.class, args);
    }
}
