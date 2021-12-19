package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    public List<Customer> findAll();

    public void deleteCustomerByCustomerID(Integer customer_id);

    public Customer getCustomerByCustomerID(Integer customer_id);
}
