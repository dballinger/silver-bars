package com.github.dballinger.silverbars;

import org.junit.Test;

import java.util.List;

import static com.github.dballinger.silverbars.SellOrderFixture.aSellOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class LiveOrdersTest {
    @Test
    public void shouldSummariseSellOrdersWithDifferentPricePerKG() throws Exception {
        List<SellOrder> orders = List.of(
         aSellOrder()
          .withQty(4)
          .withPricePerUnit(300)
          .build(),
         aSellOrder()
          .withQty(5)
          .withPricePerUnit(301)
          .build()
        );
        Summary summary = new LiveOrders(orders).summarise();

        assertThat(summary.sell(), contains(
           new SummaryItem(new Kilograms(4), new GBP(300)),
           new SummaryItem(new Kilograms(5), new GBP(301))
        ));
    }
}
