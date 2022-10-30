package com.example.activitypratique.repositories;

import com.example.activitypratique.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {

}
