package com.imocha.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan(basePackages = "com.imocha")
@SpringBootApplication
@EnableJpaAuditing
public class LeadsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeadsServiceApplication.class, args);
    }

}
