package com.github.dballinger.silverbars;

public class SummaryItem {
    private final Kilograms qty;

    private final GBP pricePerUnit;

    public SummaryItem(Kilograms qty, GBP pricePerUnit) {
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
    public String toString() {
        return "SummaryItem{" +
                "qty=" + qty +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }

}
