package com.github.dballinger.silverbars;

import java.util.Random;
import java.util.UUID;

public class SellOrderFixture {
    private final UserId userId;
    private final Kilograms qty;
    private final GBP pricePerUnit;

    private SellOrderFixture(UserId userId, Kilograms qty, GBP pricePerUnit) {
        this.userId = userId;
        this.qty = qty;
        this.pricePerUnit = pricePerUnit;
    }

    public static SellOrderFixture aSellOrder() {
        Random random = new Random();
        return new SellOrderFixture(
         new UserId(UUID.randomUUID().toString()),
         new Kilograms(random.nextInt(9) + 1),
         new GBP(random.nextInt(100) + 250)
        );
    }

    public SellOrderFixture withQty(int qty) {
        return new SellOrderFixture(userId, new Kilograms(qty), pricePerUnit);
    }

    public SellOrderFixture withPricePerUnit(int price) {
        return new SellOrderFixture(userId, qty, new GBP(price));
    }

    public SellOrder build() {
        return new SellOrder(userId, qty, pricePerUnit);
    }
}
