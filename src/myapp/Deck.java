package myapp;

//********************************************************************
//  Deck.java       Casey McCullough
//	v. 2.0
//  
//********************************************************************

/**
 * @(#)Deck.java
 *
 *
 * Don't forget to comment
 */

import java.util.*;

public class Deck {

	private ArrayList<Card> myCards;

	public Deck() {
		// 52 cards, a standard deck.
		// unshuffled, in increasing value by rank and then by suit
		myCards = new ArrayList<Card>();
		Card nextCard;
		
		for (int i = 0; i < 52; i++) {
			nextCard = new Card(i);
			myCards.add(nextCard);
		}
	}
	
	/**
	 * 
	 * @param cards cards to be converted into a Deck
	 */
	public Deck (ArrayList<Card> cards)
	{
		myCards = cards;
	}
	
	/**
	 * size()
	 * @return the number of cards in this Deck
	 */
	
	public int size()
	{
		return myCards.size();
	}

	/**
	 * places cards in a random order.  
	 * Each card in deck is assigned a random position
	 */
	public void shuffle() {
		// places cards in random order
		ArrayList<Card> shuffled = new ArrayList<Card>();
		Random gen = new Random();
		int newPos;

		while (myCards.size() > 0) {
			Card tempCard = myCards.remove(0);
			newPos = gen.nextInt(shuffled.size() + 1);
			// find random position in shuffled deck to add next card
			shuffled.add(newPos, tempCard);
		}

		myCards = shuffled;
	}

	
	/**
	 * aka, sort().  Place cards in increasing order based on rank and then suit
	 */
	public void unshuffle()
	// sorts cards from least to greatest
	// based on Comparable
	{
		Collections.sort(myCards);
	}

	/**
	 * returns a String with each card presented on its own line. 
	 */
	public String toString()
	// returns a String of 52 cards
	// each one on its own line
	{
		String theCards = "";
		for (int i = 0; i < myCards.size(); i++)
			theCards += "Card # " + (i) + ": " + myCards.get(i) + "\n";

		return theCards;

	}

	/**
	 * 
	 * @param numCards the number of cards to be removed from Deck and placed in ArrayList
	 * @return a group of numCards from the top (0 end) of the Deck
	 */
	public ArrayList<Card> deal(int numCards)
	// returns array list containing numCards cards
	// those cards, obviously, are no longer in the Deck!~
	{
		ArrayList<Card> toBeDealt = new ArrayList<Card>();
		Card tempCard;

		for (int i = 0; i < numCards; i++)

		{
			tempCard = myCards.remove(0); // why does 0 work?
			toBeDealt.add(tempCard);
		}

		return toBeDealt;

	}

	public Card peek(int i) {
		
		return myCards.get(i);
	}
	
	public Deck clone()
	{
		return new Deck ((ArrayList<Card>) myCards.clone());
	}

} // end Deck.java

