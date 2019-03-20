package com.github.dballinger.silverbars;

import com.google.common.collect.Ordering;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.github.dballinger.silverbars.OrderType.Buy;
import static com.github.dballinger.silverbars.OrderType.Sell;

public class Summary {
    private final List<SummaryItem> sell;
    private final List<SummaryItem> buy;
    private final Collector<Order, ?, Map<GBP, List<Order>>> groupByPrice = Collectors.groupingBy(Order::getPricePerUnit);

    public Summary(Collection<Order> orders) {

        List<SummaryItem> aggregatedSell = orders.stream()
                                            .filter(Sell.acceptedOrders())
                                            .collect(groupByPrice)
                                            .entrySet()
                                            .stream()
                                            .map(
                                             entry ->
                                              new SummaryItem(
                                               entry.getValue().stream().map(Order::getQty).reduce(Kilograms.ZERO, Kilograms.sum()),
                                               entry.getKey()
                                              )
                                            )
                                            .collect(Collectors.toList());
        List<SummaryItem> aggregatedBuy = orders.stream()
                                            .filter(Buy.acceptedOrders())
                                            .collect(groupByPrice)
                                            .entrySet()
                                            .stream()
                                            .map(
                                             entry ->
                                              new SummaryItem(
                                               entry.getValue().stream().map(Order::getQty).reduce(Kilograms.ZERO, Kilograms.sum()),
                                               entry.getKey()
                                              )
                                            )
                                            .collect(Collectors.toList());

        sell = Ordering.from(Sell.summaryItemSort()).sortedCopy(aggregatedSell);
        buy = Ordering.from(Buy.summaryItemSort()).sortedCopy(aggregatedBuy);
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
