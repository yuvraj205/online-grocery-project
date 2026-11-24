package com.grocery.onlinegrocery.repository;
import com.grocery.onlinegrocery.entity.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GroceryOrderRepository extends JpaRepository<GroceryOrder, Long> {}
