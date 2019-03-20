package com.github.dballinger.silverbars;

import org.junit.Before;
import org.junit.Test;

import static com.github.dballinger.silverbars.SellOrderFixture.aSellOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SilverBarsTest {

    private SilverBars silverBars;
    private Repo repo;

    @Before
    public void before() {
        repo = new Repo();
        silverBars = new SilverBars(repo);
    }

    @Test
    public void shouldRegisterAnOrder() throws Exception {
        SellOrder order = aSellOrder().build();
        silverBars.register(order);
        assertThat(repo.allOrders(), contains(order));
    }

    @Test
    public void shouldCancelAnOrder() throws Exception {
        SellOrder order = aSellOrder().build();
        OrderId id = silverBars.register(order);
        silverBars.cancel(id);
        assertThat(repo.allOrders(), is(empty()));
    }

    @Test
    public void shouldProvideLiveOrderSummary() throws Exception {
        SellOrder order1 = aSellOrder().withPricePerUnit(1).withQty(2).build();
        SellOrder order2 = aSellOrder().withPricePerUnit(3).withQty(4).build();
        repo.add(order1);
        repo.add(order2);
        Summary summary = silverBars.liveOrders();
        assertThat(summary.sell(), contains(
         samePropertyValuesAs(new SummaryItem(order1.getQty(), order1.getPricePerUnit())),
         samePropertyValuesAs(new SummaryItem(order2.getQty(), order2.getPricePerUnit()))
        ));
    }
}