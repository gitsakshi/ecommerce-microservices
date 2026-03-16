package com.sakshi.inventory_service.dto;

import lombok.Data;

@Data
public class InventoryRequest {
    private Long productId;
    private int quantity;
}
