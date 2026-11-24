package com.grocery.onlinegrocery.controller;
import com.grocery.onlinegrocery.entity.GroceryItem;
import com.grocery.onlinegrocery.service.GroceryItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/grocery-items")
public class GroceryItemController {
    private final GroceryItemService service;
    public GroceryItemController(GroceryItemService service) {
    	this.service = service; 
    	}
    @GetMapping 
    public List<GroceryItem> getAll() {
    	return service.getAll(); 
    	}
    @GetMapping("/{id}") 
    public GroceryItem getById(@PathVariable Long id) {
    	return service.getById(id); 
    }
    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED) 
    public GroceryItem create(@Valid @RequestBody GroceryItem item) {
    	return service.create(item); 
    	}
    
    @PutMapping("/{id}") 
    public GroceryItem update(@PathVariable Long id, @Valid @RequestBody GroceryItem item) {
    	return service.update(id, item); 
    	}
    @DeleteMapping("/{id}") 
    @ResponseStatus(HttpStatus.NO_CONTENT) 
    public void delete(@PathVariable Long id) {
    	service.delete(id); 
    	}
}
