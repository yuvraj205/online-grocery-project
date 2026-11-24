package com.grocery.onlinegrocery.service;

import com.grocery.onlinegrocery.entity.GroceryItem;
import com.grocery.onlinegrocery.exception.ResourceNotFoundException;
import com.grocery.onlinegrocery.repository.GroceryItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroceryItemService {
    private final GroceryItemRepository repository;
    public GroceryItemService(GroceryItemRepository repository) { this.repository = repository; }
    public List<GroceryItem> getAll() { return repository.findAll(); }
    public GroceryItem getById(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Grocery item not found with id: " + id)); }
    public GroceryItem create(GroceryItem item) { item.setId(null); return repository.save(item); }
    public GroceryItem update(Long id, GroceryItem input) {
        GroceryItem item = getById(id);
        item.setName(input.getName()); item.setCategory(input.getCategory()); item.setPrice(input.getPrice()); item.setQuantity(input.getQuantity());
        return repository.save(item);
    }
    public void delete(Long id) { repository.delete(getById(id)); }
}
