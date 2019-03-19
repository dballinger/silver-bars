package com.github.dballinger.silverbars;

import org.junit.Test;

import static com.github.dballinger.silverbars.SellOrderFixture.aSellOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RepoTest {

    @Test
    public void shouldAddAndListOrders() throws Exception {
        SellOrder order1 = aSellOrder().build();
        SellOrder order2 = aSellOrder().build();
        Repo repo = new Repo();
        repo.add(order1);
        repo.add(order2);

        assertThat(repo.allOrders(),
         containsInAnyOrder(
          samePropertyValuesAs(order1),
          samePropertyValuesAs(order2)
         )
        );
    }

    @Test
    public void shouldRemoveOrder() throws Exception {
        SellOrder orderToKeep = aSellOrder().build();
        SellOrder orderToRemove = aSellOrder().build();
        Repo repo = new Repo();
        repo.add(orderToKeep);
        OrderId id = repo.add(orderToRemove);
        repo.remove(id);
        
        assertThat(repo.allOrders(), contains(samePropertyValuesAs(orderToKeep)));
    }
}
