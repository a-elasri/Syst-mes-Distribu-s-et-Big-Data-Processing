package com.example.activitypratique.mappers;

import com.example.activitypratique.dto.CustomerRequestDTO;
import com.example.activitypratique.dto.CustomerResponseDTO;
import com.example.activitypratique.entities.Customer;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
//Utiliser spring pour injecter l'implémantation de cet interface
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);
}
