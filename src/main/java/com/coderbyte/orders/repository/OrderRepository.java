package com.coderbyte.orders.repository;

import com.coderbyte.orders.model.Order;
import reactor.core.publisher.Mono;

public interface OrderRepository {
    Mono<Order> save(Order order);
}