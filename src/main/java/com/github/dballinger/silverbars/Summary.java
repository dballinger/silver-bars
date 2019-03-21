package com.github.dballinger.silverbars;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Summary {

    private final List<SummaryItem> sell;
    private final List<SummaryItem> buy;

    Summary(Collection<Order> orders) {
        sell = summariseOrders(orders, SellSummaryItem.acceptedOrders(), SellSummaryItem::new);
        buy = summariseOrders(orders, BuySummaryItem.acceptedOrders(), BuySummaryItem::new);
    }

    private List<SummaryItem> summariseOrders(Collection<Order> orders,
                                              Predicate<Order> acceptedOrders,
                                              BiFunction<Kilograms, GBP, SummaryItem> summaryItemConstructor) {
        return orders
                .stream()
                .filter(acceptedOrders)
                .collect(groupByPrice)
                .entrySet()
                .stream()
                .map(toAggregatedSummaryItem(summaryItemConstructor))
                .sorted()
                .collect(Collectors.toList());
    }

    public List<SummaryItem> sell() {
        return sell;
    }

    public List<SummaryItem> buy() {
        return buy;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "sell=" + sell +
                '}';
    }

    private static final Collector<Order, ?, Map<GBP, List<Order>>> groupByPrice = Collectors.groupingBy(Order::getPricePerUnit);

    private static Function<Map.Entry<GBP, List<Order>>, SummaryItem> toAggregatedSummaryItem(BiFunction<Kilograms, GBP, SummaryItem> summaryItemConstructor) {
        return entry ->
                summaryItemConstructor.apply(
                 entry.getValue().stream().map(Order::getQty).reduce(Kilograms.ZERO, Kilograms.sum()),
                 entry.getKey()
                );
    }
}
