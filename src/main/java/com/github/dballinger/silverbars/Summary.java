package com.github.dballinger.silverbars;

import com.google.common.collect.Ordering;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Summary {
    private final List<SummaryItem> sell;

    Summary(List<SummaryItem> sell) {
        this.sell = Ordering.from((SummaryItem item1, SummaryItem item2) -> item1.pricePerUnit().value().compareTo(item2.pricePerUnit().value()))
                     .sortedCopy(
                      sell.stream()
                       .collect(Collectors.groupingBy(SummaryItem::pricePerUnit))
                       .values()
                       .stream()
                       .map(items -> items.stream().reduce(
                        new SummaryItem(new Kilograms(0), new GBP(0)),
                        (item1, item2) -> new SummaryItem(new Kilograms(item1.qty().value() + item2.qty().value()), item2.pricePerUnit())
                       ))
                       .collect(Collectors.toList())
                     );
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
