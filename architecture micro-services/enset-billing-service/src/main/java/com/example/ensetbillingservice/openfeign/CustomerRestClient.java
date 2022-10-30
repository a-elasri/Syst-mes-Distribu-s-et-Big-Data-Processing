package com.example.ensetbillingservice.openfeign;

import com.example.ensetbillingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//On indique le nom de service de l'autre projet
@FeignClient(name = "CUSTOMER-SERVICE")
//Cette interface nous permet d'accéder à l'autre service
//en envoyant des requêttes
public interface CustomerRestClient {
    @GetMapping(path = "/api/customers/{id}")
    Customer getCustomer(@PathVariable(name = "id") String id);
    @GetMapping(path = "/api/customers")
    List<Customer> allCustomers();
}
