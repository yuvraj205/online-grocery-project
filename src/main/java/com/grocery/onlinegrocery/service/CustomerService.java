package com.grocery.onlinegrocery.service;

import com.grocery.onlinegrocery.entity.Customer;
import com.grocery.onlinegrocery.exception.ResourceNotFoundException;
import com.grocery.onlinegrocery.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    public CustomerService(CustomerRepository repository) { this.repository = repository; }
    public List<Customer> getAll() { return repository.findAll(); }
    public Customer getById(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id)); }
    public Customer create(Customer customer) { customer.setId(null); return repository.save(customer); }
    public Customer update(Long id, Customer input) {
        Customer customer = getById(id);
        customer.setName(input.getName()); customer.setEmail(input.getEmail());
        customer.setAddress(input.getAddress()); customer.setPhone(input.getPhone());
        return repository.save(customer);
    }
    public void delete(Long id) { repository.delete(getById(id)); }
}
