package com.coderbyte.orders.service;

import com.coderbyte.orders.model.Order;
import com.coderbyte.orders.repository.InventoryRepository;
import com.coderbyte.orders.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final InventoryRepository inventoryRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(InventoryRepository inventoryRepository, OrderRepository orderRepository) {
        this.inventoryRepository = inventoryRepository;
        this.orderRepository = orderRepository;
    }

    public Mono<Order> placeOrder(Order order) {
        return inventoryRepository.isProductAvailable(order.getProductId(), order.getQuantity())
                .flatMap(available -> {
                    if (available) {
                        return orderRepository.save(order);
                    } else {
                        return Mono.error(new RuntimeException("Product not available"));
                    }
                });
    }
}
