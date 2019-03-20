package com.github.dballinger.silverbars;

import java.util.UUID;

public class OrderId extends TinyType<UUID> {

    private final UUID value;

    private OrderId(UUID value) {
        this.value = value;
    }

    @Override
    public UUID value() {
        return value;
    }

    static OrderId newId() {
        return new OrderId(UUID.randomUUID());
    }
}
