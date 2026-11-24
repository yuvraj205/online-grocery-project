package com.grocery.onlinegrocery.repository;
import com.grocery.onlinegrocery.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
