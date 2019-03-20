package com.github.dballinger.silverbars;

abstract public class TinyType<T> {
    abstract public T value();

    @Override
    public String toString() {
        return String.valueOf(value());
    }

    @Override
    public int hashCode() {
        return value().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass().equals(obj.getClass())) {
            return value().equals(((TinyType)obj).value());
        } else {
            return false;
        }
    }
}
