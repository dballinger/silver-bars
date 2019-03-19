package com.github.dballinger.silverbars;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class KilogramsTest {
    @Test
    public void shouldSumQuantities() throws Exception {
        List<Kilograms> quantities = List.of(new Kilograms(1), new Kilograms(2), new Kilograms(3));
        Kilograms sum = quantities.stream().reduce(Kilograms.ZERO, Kilograms.sum());
        assertThat(sum, is(new Kilograms(6)));
    }
}