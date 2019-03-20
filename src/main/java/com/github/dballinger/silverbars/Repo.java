package com.github.dballinger.silverbars;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Repo {
    private final Map<OrderId, Order> orders = new HashMap<>();

    OrderId add(Order order) {
        OrderId id = OrderId.newId();
        orders.put(id, order);
        return id;
    }

    Collection<Order> allOrders() {
        return Collections.unmodifiableCollection(orders.values());
    }

    void remove(OrderId id) {
        orders.remove(id);
    }
}
