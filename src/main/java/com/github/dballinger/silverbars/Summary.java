package com.github.dballinger.silverbars;

import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Summary {
    private final List<SummaryItem> sell;
    private final Comparator<SummaryItem> priceAscending = (SummaryItem item1, SummaryItem item2) -> item1.pricePerUnit().value().compareTo(item2.pricePerUnit().value());
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return Objects.equals(sell, summary.sell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sell);
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
