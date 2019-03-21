package com.github.dballinger.silverbars;

import java.util.function.Predicate;

public enum OrderType {
    Sell,
    Buy;

    Predicate<Order> acceptedOrders() {
        return order -> order.getType() == this;
    }
}
