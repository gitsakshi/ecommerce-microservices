package com.sakshi.inventory_service.model;

public class OrderPlacedEvent {

    private Long orderId;
    private Long productId;
    private int quantity;

    public OrderPlacedEvent() {}

    public OrderPlacedEvent(Long orderId, Long productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}