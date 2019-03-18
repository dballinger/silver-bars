package com.github.dballinger.silverbars;

import java.util.List;
import java.util.Objects;

public class Summary {
    private final List<SummaryItem> sell;

    Summary(List<SummaryItem> sell) {
        this.sell = sell;
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
