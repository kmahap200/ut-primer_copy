package com.formlessvoid.games.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * StandardDeck represents a normal 52 card deck of playing cards with the standard
 * suits (Spade, Heart, Diamond, Club) in the standard range (2-10, J, Q, K, A).
 * <p>
 * When constructed you can tell it if it should seed Aces as high cards or low
 * cards.  You can also specify what compare rules you want the deck to use.
 * 
 * @author richw@formlessvoid.com
 *
 */
public class StandardDeck implements Deck, Comparator<Card> {
	/**
	 * AceRules is used when setting up the deck of cards.  
	 * <ul>
	 * <li>aceHigh indicates that Aces as higher value than Kings.</li>
	 * <li>aceLow indicates that the Aces are lower than twos.</li>
	 * </ul>
	 * @author richw@formlessvoid.com
	 */
	public enum AceRules {aceHigh, aceLow};
	
	/**
	 * Compare rules let the Comparitor know how you want to handle evaluating
	 * two cards.
	 * <ul>
	 * <li>compNormal will just compare card values using Card.compare().</li>
	 * <li>compBridge will compare using the card values but use suits to break any tie.</li>
	 * </ul>
	 * @author richw@formlessvoid.com
	 */
	public enum CompareRules {compNormal, compBridge};
	
	private int deckSize = 52;
	private ArrayList<Card> deck;
	private int deckPtr; // where we are in the deck
	
	// rule markers for how we seed the deck and compare cards
	private AceRules aceRules;
	private CompareRules compRules;
	
	private String[] suits = {"S", "H", "D", "C"}; // spade, heart, diamond, club
	private int[] valuesAcesHi = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
	private int[] valuesAcesLo = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1};
	private String[] symbols = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

	
	private StandardDeck(StandardDeckBuilder sdb){
		this.aceRules = sdb.aceRules;  // the builder defaults to aceHigh
		this.compRules = sdb.compRules;  // the builder defaults to compNormal
		deck = new ArrayList<Card>(deckSize);
		seedDeck();
	}

	/**
	 * Shuffles the deck and resets the deck pointer.
	 */
	public void shuffle(){
		Collections.shuffle(this.deck, new Random(System.nanoTime()));
		this.deckPtr = 0;
	}
	
	/**
	 * Fetches n Cards from the deck.
	 * 
	 * @param how many cards you would like from the deck.
	 * @return an ArrayList of Cards from the current deckpointer on down or an empty list
	 * if there aren't enough cards left in the deck.
	 */
	public ArrayList<Card> getCards(int nCards) {
		ArrayList<Card> cards = new ArrayList<Card>();
		
		if((deckSize - deckPtr) > nCards){
			int cnt = 0;
			while(cnt < nCards){
				cards.add(this.deck.get(deckPtr + cnt));
				cnt++;
			}
			deckPtr = deckPtr + nCards;
		}
		else{ // TODO: log4j
			System.out.println("not enough cards in deck");
			throw new ArrayIndexOutOfBoundsException("not enough cards in deck");
		}
		
		return cards;
	}

	/**
	 * @return the card at the index requested or null if the request is out of bounds
	 */
	public Card getCardAt(int deckNdx){
		if(deckNdx <= this.deckSize && deckNdx >= 0){
			return this.deck.get(deckNdx);
		}
		
		return null;
	}
	
	private void seedDeck(){
		for(String suit : suits){
			for(int ndx = 0; ndx < valuesAcesHi.length; ndx++){
				Card c = null;
				if(this.aceRules == AceRules.aceHigh){
					c = new Card(valuesAcesHi[ndx], symbols[ndx], suit);
				}
				else{
					c = new Card(valuesAcesLo[ndx], symbols[ndx], suit);
				}
				this.deck.add(c);
			}
		}
	}

	/**
	 * Compare two cards using the rules for the deck (as determined by the
	 * CompareRules for the deck).
	 * 
	 * @return a positive value if card 1 is greater than card 2, 0 if they are equal
	 * and a negative value if card 1 is less than card 2.
	 */
	public int compare(Card card1, Card card2) {
		if(this.compRules.equals(CompareRules.compNormal)){
			return card1.compareTo(card2);
		}
		else if(this.compRules.equals(CompareRules.compBridge)){
			// in bridge, the 2H is "greater than" the 2C
			// so we will use the suit for tie-breaking
			// bridge order is S H D C from highest to lowest
			// lucky for us (at least in English) is that the alphbetic order
			// works on our favor so we can just use String.compareTo
			if(card1.compareTo(card2) == 0){
				return card1.getSuit().compareTo(card2.getSuit());
			}
			else{
				return card1.compareTo(card2);
			}
		}
		else {
			return card1.compareTo(card2);
		}		
	}

	/**
	 * Builder interface for constructing a standard deck of cards
	 * 
	 * @author richw@formlessvoid.com
	 *
	 */
	public static class StandardDeckBuilder{
		// sets defaults to best guess values
		private AceRules aceRules = AceRules.aceHigh;
		private CompareRules compRules = CompareRules.compNormal;
				
		public StandardDeckBuilder aceRules(AceRules rules){
			this.aceRules = rules;
			return this;
		}
		
		public StandardDeckBuilder compareRules(CompareRules rules){
			this.compRules = rules;
			return this;
		}
		
		public StandardDeck build(){
			return new StandardDeck(this);
		}
	}
	
	/*  legacy version of shuffle from the era before Collections
	public void shuffle() {
		Random r = new Random();
		for(int i = this.deckSize; i > 1; i--){
			int randLoc = r.nextInt(i);
			System.out.println("swapping " + i + " with " + randLoc);
			swapCards(i-1, randLoc);
		}
	}

	private void swapCards(int a, int b){
		Card tmp = this.deck.get(a);
		this.deck.set(a, this.deck.get(b));
		this.deck.set(b, tmp);
		tmp = null;
	}
	*/
}