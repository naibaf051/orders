package com.coderbyte.orders.repository;

import reactor.core.publisher.Mono;

public interface InventoryRepository {
    Mono<Boolean> isProductAvailable(String productId, int quantity);


}
