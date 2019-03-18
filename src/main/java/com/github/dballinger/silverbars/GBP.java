package com.github.dballinger.silverbars;

public class GBP extends TinyType<Integer> {
    private int value;

    public GBP(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }
}
