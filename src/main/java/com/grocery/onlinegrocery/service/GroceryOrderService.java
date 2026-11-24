package com.grocery.onlinegrocery.service;

import com.grocery.onlinegrocery.dto.OrderRequest;
import com.grocery.onlinegrocery.entity.*;
import com.grocery.onlinegrocery.exception.ResourceNotFoundException;
import com.grocery.onlinegrocery.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class GroceryOrderService {
    private final GroceryOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final GroceryItemRepository itemRepository;
    public GroceryOrderService(GroceryOrderRepository orderRepository, CustomerRepository customerRepository, GroceryItemRepository itemRepository) {
        this.orderRepository = orderRepository; this.customerRepository = customerRepository; this.itemRepository = itemRepository;
    }
    public List<GroceryOrder> getAll() { return orderRepository.findAll(); }
    public GroceryOrder getById(Long id) { return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id)); }
    @Transactional
    public GroceryOrder create(OrderRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + request.getCustomerId()));
        List<GroceryItem> items = loadItems(request.getGroceryItemIds());
        GroceryOrder order = new GroceryOrder(); order.setCustomer(customer); order.setGroceryItems(items); order.setTotalPrice(calculateTotal(items));
        return orderRepository.save(order);
    }
    @Transactional
    public GroceryOrder update(Long id, OrderRequest request) {
        GroceryOrder order = getById(id);
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + request.getCustomerId()));
        List<GroceryItem> items = loadItems(request.getGroceryItemIds());
        order.setCustomer(customer); order.setGroceryItems(items); order.setTotalPrice(calculateTotal(items));
        return orderRepository.save(order);
    }
    public void delete(Long id) { orderRepository.delete(getById(id)); }
    private List<GroceryItem> loadItems(List<Long> ids) {
        List<GroceryItem> items = itemRepository.findAllById(ids);
        if (items.size() != ids.stream().distinct().count()) throw new ResourceNotFoundException("One or more grocery item IDs do not exist.");
        return items;
    }
    private BigDecimal calculateTotal(List<GroceryItem> items) {
        return items.stream().map(GroceryItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
