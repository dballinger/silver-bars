package com.github.dballinger.silverbars;

import java.util.List;
import java.util.stream.Collectors;

public class LiveOrders {
    private List<SellOrder> orders;

    public LiveOrders(List<SellOrder> orders) {
        this.orders = orders;
    }

    public Summary summarise() {
        List<SummaryItem> items = orders
                                   .stream()
                                   .map(order -> new SummaryItem(order.getQty(), order.getPricePerUnit()))
                                   .collect(Collectors.toList());
        return new Summary(items);
    }
}
