package com.loseminho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LoseminhoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoseminhoApplication.class, args);
    }

}
