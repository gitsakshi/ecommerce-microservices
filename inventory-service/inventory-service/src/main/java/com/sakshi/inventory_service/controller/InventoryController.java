package com.sakshi.inventory_service.controller;

import com.sakshi.inventory_service.repository.InventoryRepository;
import com.sakshi.inventory_service.model.Inventory;
import com.sakshi.inventory_service.service.InventoryService;
import com.sakshi.inventory_service.dto.InventoryRequest;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addInventory(@RequestBody Inventory inventory) {
        inventoryRepository.save(inventory);
        return ResponseEntity.ok("Inventory added");

    }

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @PostMapping("/reduce")
    public ResponseEntity<Boolean> reduceStock(@RequestBody InventoryRequest request) {

        boolean result = inventoryService
                .reduceStock(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok(result);
    }
}
