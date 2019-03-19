package com.github.dballinger.silverbars;

public class SilverBars {
    private Repo repo;

    public SilverBars(Repo repo) {
        this.repo = repo;
    }

    public void register(SellOrder order) {
        repo.add(order);
    }
}
