package com.sakshi.order_service.exception;

import com.sakshi.order_service.exception.OutofStockException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OutofStockException.class)
    public ResponseEntity<String> handleOutOfStock(OutofStockException ex) {
        return ResponseEntity
                .badRequest()   // 400
                .body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}