package com.coderbyte.orders.config;

import com.coderbyte.orders.model.Order;
import com.coderbyte.orders.repository.OrderRepository;
import com.coderbyte.orders.repository.InventoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RepositoryConfig {

    @Bean
    public InventoryRepository inventoryRepository() {
        return new InventoryRepository() {
            @Override
            public Mono<Boolean> isProductAvailable(String productId, int quantity) {
                return Mono.just(true);
            }
        };
    }

    @Bean
    public OrderRepository orderRepository() {
        return new OrderRepository() {
            @Override
            public Mono<Order> save(Order order) {
                return Mono.just(order);
            }
        };
    }
}