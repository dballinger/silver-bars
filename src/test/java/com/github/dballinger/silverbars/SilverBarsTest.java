package com.github.dballinger.silverbars;

import org.junit.Before;
import org.junit.Test;

import static com.github.dballinger.silverbars.SellOrderFixture.aSellOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

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
}