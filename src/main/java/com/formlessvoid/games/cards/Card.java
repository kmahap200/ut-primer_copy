package com.formlessvoid.games.cards;

/**
 * Simple representation of a playing card from a 'regular' deck of cards.
 * 
 * @author richw@formlessvoid.com
 *
 */
public class Card implements Comparable<Card>{
	private int value; // ex: 2 - 10, 11, 12, 13, 14
	private String symbol; // ex: 2 - 10, J, Q, K, A
	private String suit; // ex: H, D, S, C
	
	public Card(int value, String symbol, String suit){
		this.value = value;
		this.symbol = symbol;
		this.suit = suit;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(symbol)
			.append(this.suit)
			.append(" [")
			.append(this.value)
			.append("]");
		
		return sb.toString();
	}

	public int compareTo(Card o) {
		return Integer.compare(this.getValue(), o.getValue());
	}

	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}

	public void setSymbol(String symbol){
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return this.symbol;
	}

	public void setSuit(String suit){
		this.suit = suit;
	}
	
	public String getSuit() {
		return this.suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (suit == null) {
			if (other.suit != null)
				return false;
		} else if (!suit.equals(other.suit))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (value != other.value)
			return false;
		return true;
	}
}
