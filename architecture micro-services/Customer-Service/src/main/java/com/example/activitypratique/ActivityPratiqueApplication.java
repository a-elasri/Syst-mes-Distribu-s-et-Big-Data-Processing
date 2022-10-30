package com.example.activitypratique;

import com.example.activitypratique.dto.CustomerRequestDTO;
import com.example.activitypratique.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ActivityPratiqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityPratiqueApplication.class, args);
    }
    @Bean
    CommandLineRunner start(
            CustomerService customerService
    ){
        return args -> {
            customerService.save(new CustomerRequestDTO(
                    "C01","Adria","Adria@adria.com"
            ));
            customerService.save(new CustomerRequestDTO(
                    "C02","OpenLab","Open@openLab.com"
            ));
        };
    }
}

