package com.formlessvoid.games.dice;

import java.util.Random;

public class FuzzyDie extends Die {
	private boolean fuzzFactor;
	
	public FuzzyDie(int sides) {
		super(sides);
	}
	
	public FuzzyDie(int sides, boolean fuzzFactor){
		super(sides);
		this.fuzzFactor = fuzzFactor;
	}

	public int roll(){
		if(fuzzFactor){
			int s = 0; int q = 0;
			for(int i = 0; i < 10; i++){
				s += super.roll();
				q = i;
			}
			Random r = new Random();
			int d = r.nextInt(q) + 1;
			return s/d;
		}
		else{
			return super.roll();
		}
	}
}
