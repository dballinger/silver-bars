package com.github.dballinger.silverbars;

import java.util.function.BinaryOperator;

public class Kilograms extends TinyType<Integer> {
    public static final Kilograms ZERO = new Kilograms(0);
    private int value;

    public Kilograms(int value) {
        this.value = value;
    }

    public static BinaryOperator<Kilograms> sum() {
        return (Kilograms item1, Kilograms item2) -> new Kilograms(item1.value + item2.value);
    }

    @Override
    public Integer value() {
        return value;
    }
}
