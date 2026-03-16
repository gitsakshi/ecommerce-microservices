package com.sakshi.order_service.dto;

import java.util.List;
import lombok.Data;

@Data
public class CartResponse {
    private Long userId;
    private List<CartItemDto> items;
}
