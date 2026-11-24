package com.grocery.onlinegrocery.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class GroceryOrder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "order_grocery_items",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "grocery_item_id"))
    private List<GroceryItem> groceryItems = new ArrayList<>();

    private LocalDateTime orderDate;
    private BigDecimal totalPrice;

    @PrePersist
    public void setOrderDateIfMissing() {
        if (orderDate == null) orderDate = LocalDateTime.now();
    }

    public GroceryOrder() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public List<GroceryItem> getGroceryItems() { return groceryItems; }
    public void setGroceryItems(List<GroceryItem> groceryItems) { this.groceryItems = groceryItems; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public BigDecimal getTotalPrice() { return totalPrice; }
    public void setTotalPrice(BigDecimal totalPrice) { this.totalPrice = totalPrice; }
}
