package com.grocery.onlinegrocery.controller;
import com.grocery.onlinegrocery.entity.Customer;
import com.grocery.onlinegrocery.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;
    public CustomerController(CustomerService service) {
    	this.service = service; 
    	}
    @GetMapping public List<Customer> getAll() {
    	return service.getAll(); 
    	}
    @GetMapping("/{id}") public Customer getById(@PathVariable Long id) { 
    	return service.getById(id); 
    	}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Customer create(@Valid @RequestBody Customer customer) { 
    	return service.create(customer); 
    	}
    @PutMapping("/{id}") public Customer update(@PathVariable Long id, @Valid @RequestBody Customer customer) {
    	return service.update(id, customer); 
    	}
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) {
    	service.delete(id); 
    	}
}
