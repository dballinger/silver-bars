package com.github.dballinger.silverbars;

public class SellOrder {
    private final UserId userId;
    private final Kilograms qty;
    private final GBP pricePerUnit;

    public SellOrder(UserId userId, Kilograms qty, GBP pricePerUnit) {

        this.userId = userId;
        this.qty = qty;
        this.pricePerUnit = pricePerUnit;
    }

    public UserId getUserId() {
        return userId;
    }

    public Kilograms getQty() {
        return qty;
    }

    public GBP getPricePerUnit() {
        return pricePerUnit;
    }
}
