package com.github.dballinger.silverbars;

public class SilverBars {
    private Repo repo;

    SilverBars(Repo repo) {
        this.repo = repo;
    }

    public OrderId register(Order order) {
        return repo.add(order);
    }

    public void cancel(OrderId id) {
        repo.remove(id);
    }

    public Summary liveOrders() {
        return new Summary(repo.allOrders());
    }

    public static SilverBars newInstance() {
        return new SilverBars(new Repo());
    }
}
