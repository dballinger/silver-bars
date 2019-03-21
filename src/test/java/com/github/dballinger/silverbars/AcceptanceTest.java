package com.github.dballinger.silverbars;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.dballinger.silverbars.OrderFixture.aBuyOrder;
import static com.github.dballinger.silverbars.OrderFixture.aSellOrder;
import static com.google.common.collect.Iterables.getOnlyElement;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

public class AcceptanceTest {

    private SilverBars silverBars;

    @Before
    public void before() {
        silverBars = new SilverBars(new Repo());
    }

    @Test
    public void shouldRegisterAnOrder() throws Exception {
        Order order = aSellOrder().build();
        silverBars.register(order);
        List<SummaryItem> sellSummary = silverBars.liveOrders().sell();
        assertThat(sellSummary, contains(samePropertyValuesAs(new SellSummaryItem(order.getQty(), order.getPricePerUnit()))));
    }

    @Test
    public void shouldCancelAnOrder() throws Exception {
        Order order = aSellOrder().build();
        OrderId id = silverBars.register(order);
        silverBars.cancel(id);
        List<SummaryItem> sellSummary = silverBars.liveOrders().sell();
        assertThat(sellSummary, is(empty()));
    }

    @Test
    public void shouldSummariseSellAndBuyOrderSeparately() throws Exception {
        Order sellOrder = aSellOrder().build();
        Order buyOrder = aBuyOrder().build();
        silverBars.register(sellOrder);
        silverBars.register(buyOrder);
        Summary summary = silverBars.liveOrders();
        assertThat(summary.sell(), contains(samePropertyValuesAs(new SellSummaryItem(sellOrder.getQty(), sellOrder.getPricePerUnit()))));
        assertThat(summary.buy(), contains(samePropertyValuesAs(new BuySummaryItem(buyOrder.getQty(), buyOrder.getPricePerUnit()))));
    }

    @Test
    public void shouldSortSellOrdersByPriceAscending() throws Exception {
        silverBars.register(aSellOrder().withPricePerUnit(1).build());
        silverBars.register(aSellOrder().withPricePerUnit(2).build());
        List<Integer> prices = silverBars.liveOrders().sell().stream().map(item -> item.getPricePerUnit().value()).collect(Collectors.toList());
        assertThat(prices, contains(1, 2));
    }

    @Test
    public void shouldSortBuyOrdersByPriceDescending() throws Exception {
        silverBars.register(aBuyOrder().withPricePerUnit(1).build());
        silverBars.register(aBuyOrder().withPricePerUnit(2).build());
        List<Integer> prices = silverBars.liveOrders().buy().stream().map(item -> item.getPricePerUnit().value()).collect(Collectors.toList());
        assertThat(prices, contains(2, 1));
    }

    @Test
    public void shouldSumSimilarOrders() throws Exception {
        silverBars.register(aBuyOrder().withQty(1).withPricePerUnit(1).build());
        silverBars.register(aBuyOrder().withQty(2).withPricePerUnit(1).build());
        silverBars.register(aSellOrder().withQty(3).withPricePerUnit(1).build());
        silverBars.register(aSellOrder().withQty(4).withPricePerUnit(1).build());
        Summary summary = silverBars.liveOrders();
        Double buyQty = getOnlyElement(summary.buy()).getQty().value();
        Double sellQty = getOnlyElement(summary.sell()).getQty().value();
        assertThat(buyQty, is(3.0));
        assertThat(sellQty, is(7.0));
    }
}
