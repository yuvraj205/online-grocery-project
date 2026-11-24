package com.grocery.onlinegrocery.controller;
import com.grocery.onlinegrocery.dto.OrderRequest;
import com.grocery.onlinegrocery.entity.GroceryOrder;
import com.grocery.onlinegrocery.service.GroceryOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/orders")
public class GroceryOrderController {
    private final GroceryOrderService service;
    public GroceryOrderController(GroceryOrderService service) {
    	this.service = service; 
    	}
    @GetMapping 
    public List<GroceryOrder> getAll() {
    	return service.getAll(); 
    	}
    @GetMapping("/{id}") 
    public GroceryOrder getById(@PathVariable Long id) {
    	return service.getById(id); 
    	}
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroceryOrder create(@Valid @RequestBody OrderRequest request) {
    	return service.create(request); 
    	}
    @PutMapping("/{id}") 
    public GroceryOrder update(@PathVariable Long id, @Valid @RequestBody OrderRequest request) {
    	return service.update(id, request); 
    	}
    @DeleteMapping("/{id}") 
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void delete(@PathVariable Long id) {
    	service.delete(id); 
    	}
}
