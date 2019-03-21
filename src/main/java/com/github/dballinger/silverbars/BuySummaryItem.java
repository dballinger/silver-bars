package com.github.dballinger.silverbars;

import java.util.function.Predicate;

public class BuySummaryItem implements SummaryItem {
    private final Kilograms qty;

    private final GBP pricePerUnit;

    BuySummaryItem(Kilograms qty, GBP pricePerUnit) {
        this.qty = qty;
        this.pricePerUnit = pricePerUnit;
    }

    public GBP getPricePerUnit() {
        return pricePerUnit;
    }

    public Kilograms getQty() {
        return qty;
    }

    @Override
    public int compareTo(SummaryItem o) {
        return o.getPricePerUnit().compareTo(pricePerUnit);
    }

    static Predicate<Order> acceptedOrders() {
        return (Order order) -> order.getType() == OrderType.Buy;
    }
}
