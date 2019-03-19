package com.github.dballinger.silverbars;

import java.util.UUID;

public class OrderId extends TinyType<UUID> {

    private final UUID value;

    public OrderId(UUID value) {
        this.value = value;
    }

    @Override
    public UUID value() {
        return value;
    }

    public static OrderId newId() {
        return new OrderId(UUID.randomUUID());
    }
}
