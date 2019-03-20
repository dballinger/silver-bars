package com.github.dballinger.silverbars;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Repo {
    private final Map<OrderId, Order> orders = new HashMap<>();

    public OrderId add(Order order) {
        OrderId id = OrderId.newId();
        orders.put(id, order);
        return id;
    }

    public Collection<Order> allOrders() {
        return Collections.unmodifiableCollection(orders.values());
    }

    public void remove(OrderId id) {
        orders.remove(id);
    }
}
