package com.formlessvoid.games.dice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class DieTest {
  @Test
  public void testDie() {
	  int n = 10;
	  Die die = new Die(n);
	  int result = die.roll();
	  assertTrue((result >= 1 && result <= n));
  }
}
