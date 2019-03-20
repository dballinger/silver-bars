package com.github.dballinger.silverbars;

import java.util.function.BinaryOperator;

public class Kilograms extends TinyType<Double> {
    public static final Kilograms ZERO = new Kilograms(0);
    private double value;

    public Kilograms(double value) {
        this.value = value;
    }

    public static BinaryOperator<Kilograms> sum() {
        return (Kilograms item1, Kilograms item2) -> new Kilograms(item1.value + item2.value);
    }

    @Override
    public Double value() {
        return value;
    }
}
