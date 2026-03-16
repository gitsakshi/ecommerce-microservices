package com.sakshi.order_service.model;

public class InventoryConfirmedEvent {

    private Long orderId;

    public InventoryConfirmedEvent() {}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
