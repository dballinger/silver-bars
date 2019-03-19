package com.github.dballinger.silverbars;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Repo {
    private final Map<OrderId, SellOrder> orders = new HashMap<>();

    public OrderId add(SellOrder order) {
        OrderId id = OrderId.newId();
        orders.put(id, order);
        return id;
    }

    public Collection<SellOrder> allOrders() {
        return orders.values();
    }

    public void remove(OrderId id) {
        orders.remove(id);
    }
}
