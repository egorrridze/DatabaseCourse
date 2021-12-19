package com.example.gradebookbackend.repositories;

import com.example.gradebookbackend.models.Customer;
import com.example.gradebookbackend.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    public List<Customer> findByOrderByCustomerIDAsc();

    public void deleteCustomerByCustomerID(Integer id);

    public Customer getCustomerByCustomerID(Integer id);

    @Query(value = "select * from customers c where c.name like %:keyword% ", nativeQuery = true)
    List<Customer> findByKeyword(@Param("keyword") String keyword);
}
