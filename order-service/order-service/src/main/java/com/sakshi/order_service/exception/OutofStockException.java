package com.sakshi.order_service.exception;

public class OutofStockException extends RuntimeException{
    public OutofStockException(String message){
        super(message);
    }
}
