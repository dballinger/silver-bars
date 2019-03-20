package com.github.dballinger.silverbars;

import java.util.function.Predicate;

public enum OrderType {
    Sell,
    Buy;

    public Predicate<Order> acceptedOrders() {
        return order -> order.getType() == this;
    }
}
