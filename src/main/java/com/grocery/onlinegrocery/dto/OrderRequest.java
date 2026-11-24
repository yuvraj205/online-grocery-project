package com.grocery.onlinegrocery.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class OrderRequest {
    @NotNull private Long customerId;
    @NotEmpty private List<Long> groceryItemIds;
    public Long getCustomerId() {
    	return customerId; 
    	}
    public void setCustomerId(Long customerId) { 
    	this.customerId = customerId; 
    	}
    public List<Long> getGroceryItemIds() {
    	return groceryItemIds; 
    	}
    public void setGroceryItemIds(List<Long> groceryItemIds) {
    	this.groceryItemIds = groceryItemIds; 
    	}
}
