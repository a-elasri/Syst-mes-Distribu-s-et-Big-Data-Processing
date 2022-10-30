package com.example.ensetbillingservice;

import com.example.ensetbillingservice.dto.InvoiceRequestDTO;
import com.example.ensetbillingservice.service.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class EnsetBillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnsetBillingServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(
            InvoiceService invoiceService
    ){
        return args -> {
            invoiceService.save(new InvoiceRequestDTO(
                    BigDecimal.valueOf(76000),"C01"
            ));
            invoiceService.save(new InvoiceRequestDTO(
                    BigDecimal.valueOf(54200),"C01"
            ));
            invoiceService.save(new InvoiceRequestDTO(
                    BigDecimal.valueOf(12000),"C02"
            ));
        };
    }

}
