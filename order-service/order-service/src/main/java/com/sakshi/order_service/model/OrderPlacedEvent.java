package com.sakshi.order_service.model;

public class OrderPlacedEvent {

    private Long orderId;
    private Long productId;
    private int qty;

    public OrderPlacedEvent(Long orderId, Long productId, int qty) {
        this.orderId = orderId;
        this.productId = productId;
        this.qty = qty;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return qty;
    }
}