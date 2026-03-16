package com.sakshi.order_service.model;

public enum OrderStatus {
    CREATED,
    PAYMENT_PENDING,
    PAID,
    CANCELLED,
    INVENTORY_CONFIRMED, FAILED
}