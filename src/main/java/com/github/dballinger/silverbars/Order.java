package com.github.dballinger.silverbars;

public class Order {
    private final UserId userId;
    private final Kilograms qty;
    private final GBP pricePerUnit;
    private final OrderType type;

    public Order(UserId userId, Kilograms qty, GBP pricePerUnit, OrderType type) {
        this.userId = userId;
        this.qty = qty;
        this.pricePerUnit = pricePerUnit;
        this.type = type;
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

    public OrderType getType() {
        return type;
    }
}
