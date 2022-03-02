package com.imocha.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.imocha")
@SpringBootApplication
public class LeadsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeadsServiceApplication.class, args);
    }

}
