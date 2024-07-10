package com.coderbyte.orders.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Order {

    @NotNull
    private String productId;

    @Positive
    private int quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
