package com.sakshi.inventory_service.service;

import com.sakshi.inventory_service.model.Inventory;
import com.sakshi.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional
    public boolean reduceStock(Long productId, int quantity) {

        Inventory inventory = inventoryRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock for product " + productId);
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);

        inventoryRepository.save(inventory);

        return true;
    }
}
