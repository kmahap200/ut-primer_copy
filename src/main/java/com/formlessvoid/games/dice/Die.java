package com.formlessvoid.games.dice;

import java.util.Random;

/**
 * Simple representation of an n-sided Die and a way to roll it.
 * 
 * @author richw@formlessvoid.com
 *
 */
public class Die {
	private int sides;
	
	public Die(int sides){
		this.sides = sides;
	}
	
	/**
	 * "Rolls" the die for you.
	 * 
	 * @return
	 * @throws IllegalArgumentException if you've set up the die with a non positive value.
	 */
	public int roll(){
		Random r = new Random();
		return r.nextInt(this.sides) + 1;		
	}	
}