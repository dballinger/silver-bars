package com.github.dballinger.silverbars;

import java.util.ArrayList;
import java.util.Collection;

public class Repo {
    private final ArrayList<SellOrder> orders = new ArrayList<SellOrder>();

    public void add(SellOrder order) {
        orders.add(order);
    }

    public Collection<SellOrder> allOrders() {
        return orders;
    }
}
