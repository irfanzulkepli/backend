package com.imocha.lms;

import com.imocha.common.audit.impl.AuditorAwareImpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan(basePackages = "com.imocha")
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class LeadsServiceApplication {

    @Bean
    public AuditorAware<String> auditorAware(){
        return new AuditorAwareImpl();
    }

    public static void main(String[] args) {
        SpringApplication.run(LeadsServiceApplication.class, args);
    }

}
