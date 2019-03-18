package com.github.dballinger.silverbars;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SkeletonTest {
  @Test
  public void shouldGreet() throws Exception {
    assertThat(new Skeleton().greet(), is("Hello"));
  }
}
