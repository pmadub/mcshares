package com.mcb.mcsharesproject.repositories.customer;

import com.mcb.mcsharesproject.entities.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, String>, CustomizedCustomerRepository {

    @Query("SELECT DISTINCT c FROM CUSTOMER c WHERE UPPER(c.customerName) LIKE UPPER(CONCAT('%', :customerName, '%'))")
    List<Customer> findByCustomerNameContainingIgnoreCase(@Param("customerName") String customerName);


}
