package com.sakshi.order_service.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class CartItemDto {
    private Long productId;
    private Integer quantity;
    private BigDecimal priceAtTime;
    private Long orderId;
    //private String skuCode;

}
