package com.github.dballinger.silverbars;

import org.junit.Test;

import java.util.List;

import static com.github.dballinger.silverbars.SellOrderFixture.aSellOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class SummaryTest {
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
        Summary summary = new Summary(orders);

        assertThat(summary.sell(), contains(
         samePropertyValuesAs(new SummaryItem(new Kilograms(4), new GBP(300))),
         samePropertyValuesAs(new SummaryItem(new Kilograms(5), new GBP(301)))
        ));
    }

    @Test
    public void shouldSummariseSellOrdersInAscendingPriceOrder() throws Exception {
        List<SellOrder> orders = List.of(
         aSellOrder()
          .withQty(4)
          .withPricePerUnit(301)
          .build(),
         aSellOrder()
          .withQty(5)
          .withPricePerUnit(300)
          .build()
        );
        Summary summary = new Summary(orders);

        assertThat(summary.sell(), contains(
         samePropertyValuesAs(new SummaryItem(new Kilograms(5), new GBP(300))),
         samePropertyValuesAs(new SummaryItem(new Kilograms(4), new GBP(301)))
        ));
    }

    @Test
    public void shouldAggregateQtysForSellOrdersWithTheSamePricePerUnit() throws Exception {
        List<SellOrder> orders = List.of(
         aSellOrder()
          .withQty(4)
          .withPricePerUnit(301)
          .build(),
         aSellOrder()
          .withQty(5)
          .withPricePerUnit(300)
          .build(),
         aSellOrder()
          .withQty(2)
          .withPricePerUnit(301)
          .build()
        );
        Summary summary = new Summary(orders);

        assertThat(summary.sell(), contains(
         samePropertyValuesAs(new SummaryItem(new Kilograms(5), new GBP(300))),
         samePropertyValuesAs(new SummaryItem(new Kilograms(6), new GBP(301)))
        ));
    }
}