package com.github.dballinger.silverbars;

public class GBP extends TinyType<Integer> implements Comparable<GBP> {
    private int value;

    public GBP(int value) {
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public int compareTo(GBP o) {
        return Integer.compare(value, o.value);
    }
}
