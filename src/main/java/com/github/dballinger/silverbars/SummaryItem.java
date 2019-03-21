package com.github.dballinger.silverbars;

public interface SummaryItem extends Comparable<SummaryItem> {

    GBP getPricePerUnit();

    Kilograms getQty();

}
