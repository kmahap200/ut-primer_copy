package com.formlessvoid.games.cards;

import java.util.ArrayList;

/**
 * Defines the contract of things that a Deck should implement.
 * 
 * @author richw@formlessvoid.com
 *
 */
public interface Deck {
	/**
	 * At a minimum, this should reorder the deck;
	 */
	public void shuffle();
	
	/**
	 * Fetch a certain number of cards from the deck.
	 * 
	 * @param nCards the number of cards you would like
	 * @return An ArrayList of those cards.  Implementors are encouraged to return
	 * an empty ArrayList if the request for cards can't be processed.
	 */
	public ArrayList<Card> getCards(int nCards);
	
	/**
	 * Fetch a card from a particular location in the deck.
	 * 
	 * @param deckIndex the location in the deck you'd like the card fetched from
	 * @return The card at that index.  Implementors are encouraged to return null if the
	 * request can't be processed.
	 */
	public Card getCardAt(int deckIndex);
}
