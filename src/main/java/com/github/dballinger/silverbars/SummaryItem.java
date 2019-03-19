package com.github.dballinger.silverbars;

import java.util.Objects;

public class SummaryItem {
    private final Kilograms qty;

    public GBP getPricePerUnit() {
        return pricePerUnit;
    }

    private final GBP pricePerUnit;

    public SummaryItem(Kilograms qty, GBP pricePerUnit) {
        this.qty = qty;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummaryItem that = (SummaryItem) o;
        return Objects.equals(qty, that.qty) &&
                Objects.equals(pricePerUnit, that.pricePerUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qty, pricePerUnit);
    }

    @Override
    public String toString() {
        return "SummaryItem{" +
                "qty=" + qty +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
}
