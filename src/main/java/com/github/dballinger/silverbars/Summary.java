package com.github.dballinger.silverbars;

import com.google.common.collect.Ordering;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.github.dballinger.silverbars.OrderType.Buy;
import static com.github.dballinger.silverbars.OrderType.Sell;

public class Summary {
    private static final Collector<Order, ?, Map<GBP, List<Order>>> groupByPrice = Collectors.groupingBy(Order::getPricePerUnit);
    private static final Function<Map.Entry<GBP, List<Order>>, SummaryItem> toAggregatedSummaryItem = entry ->
                                                                                                       new SummaryItem(
                                                                                                        entry.getValue().stream().map(Order::getQty).reduce(Kilograms.ZERO, Kilograms.sum()),
                                                                                                        entry.getKey()
                                                                                                       );

    private final List<SummaryItem> sell;
    private final List<SummaryItem> buy;

    Summary(Collection<Order> orders) {
        Stream<Order> sellOrders = orders.stream().filter(Sell.acceptedOrders());
        Stream<Order> buyOrders = orders.stream().filter(Buy.acceptedOrders());

        List<SummaryItem> aggregatedSell = aggregateOrders(sellOrders);
        List<SummaryItem> aggregatedBuy = aggregateOrders(buyOrders);

        sell = Ordering.from(Sell.summaryItemSort()).sortedCopy(aggregatedSell);
        buy = Ordering.from(Buy.summaryItemSort()).sortedCopy(aggregatedBuy);
    }

    private List<SummaryItem> aggregateOrders(Stream<Order> sellOrders) {
        return sellOrders
                .collect(groupByPrice)
                .entrySet()
                .stream()
                .map(toAggregatedSummaryItem)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Summary{" +
                "sell=" + sell +
                '}';
    }

    public List<SummaryItem> sell() {
        return sell;
    }

    public List<SummaryItem> buy() {
        return buy;
    }
}
