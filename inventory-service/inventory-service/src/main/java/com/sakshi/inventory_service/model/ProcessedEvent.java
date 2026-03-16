package com.sakshi.inventory_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "processed_events")
public class ProcessedEvent {

    @Id
    private Long orderId;

    public ProcessedEvent(){}

    public ProcessedEvent(Long orderId){
        this.orderId = orderId;
    }

    public Long getOrderId(){
        return orderId;
    }
}
