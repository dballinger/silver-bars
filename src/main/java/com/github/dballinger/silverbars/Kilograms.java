package com.github.dballinger.silverbars;

public class Kilograms extends TinyType<Integer> {
    private int value;

    public Kilograms(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
