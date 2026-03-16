package com.sakshi.inventory_service.model;

public class InventoryConfirmedEvent {

    private Long orderId;
    public InventoryConfirmedEvent(){}
    public InventoryConfirmedEvent(Long orderId){
        this.orderId = orderId;
    }

    public Long getOrderId(){
        return orderId;
    }
}
