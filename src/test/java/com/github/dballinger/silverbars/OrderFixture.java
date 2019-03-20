package com.github.dballinger.silverbars;

import java.util.Random;
import java.util.UUID;

public class OrderFixture {
    private final UserId userId;
    private final Kilograms qty;
    private final GBP pricePerUnit;
    private final OrderType type;

    private OrderFixture(UserId userId, Kilograms qty, GBP pricePerUnit, OrderType type) {
        this.userId = userId;
        this.qty = qty;
        this.pricePerUnit = pricePerUnit;
        this.type = type;
    }

    public static OrderFixture aBuyOrder() {
        return anOrder(OrderType.Buy);
    }
    public static OrderFixture aSellOrder() {
        return anOrder(OrderType.Sell);
    }

    private static OrderFixture anOrder(OrderType type) {
        Random random = new Random();
        return new OrderFixture(
         new UserId(UUID.randomUUID().toString()),
         new Kilograms(random.nextInt(9) + 1),
         new GBP(random.nextInt(100) + 250),
         type
        );
    }

    public OrderFixture withQty(int qty) {
        return new OrderFixture(userId, new Kilograms(qty), pricePerUnit, type);
    }

    public OrderFixture withPricePerUnit(int price) {
        return new OrderFixture(userId, qty, new GBP(price), type);
    }

    public Order build() {
        return new Order(userId, qty, pricePerUnit, type);
    }
}
