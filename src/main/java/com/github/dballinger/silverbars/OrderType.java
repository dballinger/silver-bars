package com.github.dballinger.silverbars;

import java.util.Comparator;
import java.util.function.Predicate;

public enum OrderType {
    Sell((SummaryItem item1, SummaryItem item2) -> item1.getPricePerUnit().value().compareTo(item2.getPricePerUnit().value())),
    Buy((SummaryItem item1, SummaryItem item2) -> item2.getPricePerUnit().value().compareTo(item1.getPricePerUnit().value()));

    private Comparator<SummaryItem> summaryItemSort;

    OrderType(Comparator<SummaryItem> summaryItemSort) {
        this.summaryItemSort = summaryItemSort;
    }

    Comparator<SummaryItem> summaryItemSort() {
        return summaryItemSort;
    }

    Predicate<Order> acceptedOrders() {
        return order -> order.getType() == this;
    }
}
