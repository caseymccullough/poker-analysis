import java.util.ArrayList;
import java.util.Random;

/**
 * DeckTester.java
 *
 * 
 * @author Casey McCullough
 * @version 1.00 2007/10/2
 * Displays key methods of the Deck class.
 */

public class DeckTester {

	public static void main(String[] args) {

		Deck theDeck = new Deck();
		System.out.println(theDeck);

		theDeck.shuffle();
		System.out.println("\nShuffled: ");
		System.out.println(theDeck);

		ArrayList<Card> fiveCards = theDeck.deal(5);

		System.out.println("Five card hand:  ");
		for (int i = 0; i < fiveCards.size(); i++)
			System.out.println("Card # " + (i) + ": " + fiveCards.get(i));

		System.out.println("\nRemaining Deck: \n" + theDeck);

		theDeck.unshuffle();

		System.out.println("Five card hand:  ");
		for (int i = 0; i < fiveCards.size(); i++)
			System.out.println("Card # " + (i) + ": " + fiveCards.get(i));

		System.out.println("\nSorted Deck: \n" + theDeck);

	}
}
