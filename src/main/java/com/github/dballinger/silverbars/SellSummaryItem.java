package com.github.dballinger.silverbars;

public class SellSummaryItem implements SummaryItem {
    private final Kilograms qty;

    private final GBP pricePerUnit;

    SellSummaryItem(Kilograms qty, GBP pricePerUnit) {
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
    public int compareTo(SummaryItem o) {
        return pricePerUnit.compareTo(o.getPricePerUnit());
    }
}
