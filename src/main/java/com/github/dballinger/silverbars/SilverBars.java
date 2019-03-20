package com.github.dballinger.silverbars;

public class SilverBars {
    private Repo repo;

    public SilverBars(Repo repo) {
        this.repo = repo;
    }

    public OrderId register(SellOrder order) {
        return repo.add(order);
    }

    public void cancel(OrderId id) {
        repo.remove(id);
    }

    public Summary liveOrders() {
        return new Summary(repo.allOrders());
    }
}
