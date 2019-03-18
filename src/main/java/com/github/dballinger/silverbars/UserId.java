package com.github.dballinger.silverbars;

public class UserId extends TinyType<String> {
    private final String value;

    public UserId(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }
}
