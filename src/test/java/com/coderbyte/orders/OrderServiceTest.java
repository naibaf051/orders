package com.coderbyte.orders;

import com.coderbyte.orders.model.Order;
import com.coderbyte.orders.repository.InventoryRepository;
import com.coderbyte.orders.repository.OrderRepository;
import com.coderbyte.orders.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void placeOrder_ProductAvailable_ShouldSaveOrder() {
        Order order = new Order();
        order.setProductId("product1");
        order.setQuantity(10);

        when(inventoryRepository.isProductAvailable(any(String.class), any(Integer.class)))
                .thenReturn(Mono.just(true));
        when(orderRepository.save(any(Order.class)))
                .thenReturn(Mono.just(order));

        StepVerifier.create(orderService.placeOrder(order))
                .expectNext(order)
                .verifyComplete();
    }

    @Test
    public void placeOrder_ProductNotAvailable_ShouldThrowError() {
        Order order = new Order();
        order.setProductId("product1");
        order.setQuantity(10);

        when(inventoryRepository.isProductAvailable(any(String.class), any(Integer.class)))
                .thenReturn(Mono.just(false));

        StepVerifier.create(orderService.placeOrder(order))
                .expectError(RuntimeException.class)
                .verify();
    }
}
