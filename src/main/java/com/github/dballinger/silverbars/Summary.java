package com.github.dballinger.silverbars;

import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Summary {
    private final List<SummaryItem> sell;
    private final Comparator<SummaryItem> priceAscending = (SummaryItem item1, SummaryItem item2) -> item1.getPricePerUnit().value().compareTo(item2.getPricePerUnit().value());
    private final Collector<SellOrder, ?, Map<GBP, List<SellOrder>>> groupByPrice = Collectors.groupingBy(SellOrder::getPricePerUnit);

    public Summary(List<SellOrder> sellOrders) {
        List<SummaryItem> aggregatedSell = sellOrders.stream()
                                            .collect(groupByPrice)
                                            .entrySet()
                                            .stream()
                                            .map(
                                             entry ->
                                              new SummaryItem(
                                               entry.getValue().stream().map(SellOrder::getQty).reduce(Kilograms.ZERO, Kilograms.sum()),
                                               entry.getKey()
                                              )
                                            )
                                            .collect(Collectors.toList());

        sell = Ordering.from(priceAscending).sortedCopy(aggregatedSell);
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
}
