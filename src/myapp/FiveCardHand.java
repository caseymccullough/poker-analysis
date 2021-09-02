package myapp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FiveCardHand {

	private static final String[] RANKS = { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
			"Jack", "Queen", "King", "Ace" };

	private static final String[] SUITS = { "Hearts", "Clubs", "Diamonds", "Spades" };
	
	final static int HIGH_CARD = 0;
	final static int PAIR = 1;
	final static int TWO_PAIR = 2;
	final static int THREE_OF_KIND = 3;
	final static int STRAIGHT = 4;
	final static int FLUSH = 5;
	final static int FULL_HOUSE = 6;
	final static int FOUR_OF_KIND = 7;
	final static int STRAIGHT_FLUSH = 8;
	final static int ROYAL_FLUSH = 9;

	final static int HAND_VALUE_ADJUSTMENT = 10000;
	// multiplier so hand specific information can be added in the other four
	// digits.
	// for example, two pair would be indicated by
	// 2 * HAND_VALUE_ADJUSTMENT + position of the higher hand * 100 + position
	// of lower hand

	private ArrayList<Card> myCards;

	/**
	 * generate new hand with 5 cards
	 * 
	 * @param theCards
	 *            5 cards passed in
	 */
	public FiveCardHand(ArrayList<Card> theCards) {
		myCards = theCards;
	}

	/**
	 * Generates five cards from 5 integers. Each int is translated into the
	 * corresponding card value.
	 * 
	 * @param c1
	 *            first card
	 * @param c2
	 * @param c3
	 * @param c4
	 * @param c5
	 *            fifth card
	 */
	public FiveCardHand(int c1, int c2, int c3, int c4, int c5) {
		myCards = new ArrayList<Card>();

		myCards.add(new Card(c1));
		myCards.add(new Card(c2));
		myCards.add(new Card(c3));
		myCards.add(new Card(c4));
		myCards.add(new Card(c5));

	}
	
	public void add(Card c) {
		
		if (myCards.size() < 5)
		{
			myCards.add(c);
		}
		else
		{
			System.out.println ("ERROR! ");
			System.out.print("Hand size: " + myCards.size());
		}
		
	}
	
	public void remove(boolean[] replace) {
		
		ArrayList<Card> remainingCards = new ArrayList<Card>();
		for (int pos = 0; pos < replace.length; pos++)
		{
			if (!replace[pos]) // if card is not to be replaced . . .
			{
				remainingCards.add(myCards.get(pos));
			}
		}
		myCards = remainingCards;
		
	}
	
	public Card remove (int pos)
	{
		return myCards.remove(pos);
	}
	
	/**
	 * 
	 * @return the cards in the hand. 
	 */
	public ArrayList getCards() {
		return myCards;
	}
	
	/**
	 * 
	 * @return number of cards currently in the hand
	 */
	public int size()
	{
		return myCards.size();
	}

	/**
	 * Shows all five cards in the hand, each on its own line.
	 */
	public String toString() {
		String theCards = "FIVE CARDS:\n";
		for (int i = 0; i < myCards.size(); i++)
			theCards += "Card # " + (i + 1) + ": " + myCards.get(i) + "\n";

		return theCards;

	}

	/**
	 * Swap unwanted cards with cards from the Deck
	 * 
	 * @param replace
	 *            boolean array corresponding with 5 positions of the hand. For
	 *            each card true means replace, false means keep.
	 * @param deckCards
	 *            the Deck from which replacement cards will be drawn
	 * @return the discarded cards
	 */

	public ArrayList<Card> exchange(boolean[] replace, Deck deckCards) {
		ArrayList<Card> discards = new ArrayList<Card>();
		Card newCard;
		Card oldCard;

		for (int i = 0; i < 5; i++)
			if (replace[i] == true) {
				newCard = deckCards.deal(1).get(0);
				oldCard = myCards.set(i, newCard);
				discards.add(oldCard);
			}

		// for each card in 5-card hand, if replace is true
		// replace corresponding position in myCards with
		// new Card from deckCards

		// outputs result of each swap

		return discards;

	}

	/**
	 * Evaluates hand according to rules of Poker
	 * 
	 * @return an integer value based on highest assessment of the given hand
	 *         higher number = better hand number will eventually be multiplied
	 *         by a factor of 10,000 to leave room for 2 2-digit indicators of
	 *         cards 0 = high card 1 = pair 2 = two pair 3 = three of a kind 4 =
	 *         straight 5 = flush 6 = full house 7 = four of a kind 8 = straight
	 *         flush 9 = royal flush
	 * 
	 */
	public int getValue() {

		// sort?

		// establish ordered counts of ranks and suits
		// to facilitate identification of hands
		int[] rankCount = getRankCount();
		int[] suitCount = getSuitCount();

		int threeOfKindRank = -1;
		int firstPairRank = -1;
		int secondPairRank = -1;

		for (int pos = 0; pos < rankCount.length; pos++) {
			if (rankCount[pos] == 4) {
				return FOUR_OF_KIND * HAND_VALUE_ADJUSTMENT + pos * 100;
			}
			if (rankCount[pos] == 3) {
				threeOfKindRank = pos;
			}
			if (rankCount[pos] == 2) {
				if (firstPairRank == -1) {
					firstPairRank = pos;
				} else {
					secondPairRank = pos;
				}
			}
		}

		if (threeOfKindRank != -1) // contains three of same rank.
		{
			if (isFullHouse(threeOfKindRank, firstPairRank)) {
				return FULL_HOUSE * HAND_VALUE_ADJUSTMENT + 100 * threeOfKindRank + firstPairRank;
			}
			return THREE_OF_KIND * HAND_VALUE_ADJUSTMENT + 100 * threeOfKindRank;
		}

		if (firstPairRank != -1) // contains at least one pair
		{
			if (isTwoPair(firstPairRank, secondPairRank)) {
				
				return TWO_PAIR * HAND_VALUE_ADJUSTMENT + secondPairRank * 100 + firstPairRank;
			}
			// single pair
			return PAIR * HAND_VALUE_ADJUSTMENT + firstPairRank * 100;
		}

		// no matching ranks. Next set of tests.
		// ************************************

		int straightPos = findStraight(rankCount);
		int flushPos = findFlush(suitCount);

		if (straightPos != -1) // it's a straight
		{
			if (flushPos != -1) // it's a flush
			{
				if (straightPos == 12) // it's a royal flush
				{
					return ROYAL_FLUSH * HAND_VALUE_ADJUSTMENT + flushPos * 100;
				}

				return STRAIGHT_FLUSH * HAND_VALUE_ADJUSTMENT + myCards.get(4).getID() * 100;
			}

			return STRAIGHT * HAND_VALUE_ADJUSTMENT + straightPos * 100;

		}

		if (flushPos != -1) // still could be just a plain flush
		{
			return FLUSH * HAND_VALUE_ADJUSTMENT + flushPos * 100;
		}

		// High Card
		// Sort 5 cards

		Collections.sort(myCards);
		Card topCard = myCards.get(4);
		Card secondCard = myCards.get(3);

		return HIGH_CARD + topCard.getID() * 100; // int value of highest card.
	}

	/**
	 * 
	 * @param suitCount
	 *            array of counts of individual suits
	 * @return -1 if no flush found or 0--3 to indicate suit where flush IS
	 *         found.
	 */
	private int findFlush(int[] suitCount) {

		for (int i = 0; i < suitCount.length; i++) {
			if (suitCount[i] == 5)
				return i;
		}

		return -1;
	}

	/**
	 * 
	 * @param firstPairLocation
	 *            location of one pair or -1
	 * @param secondPairLocation
	 *            location of second pair or -1
	 * @return true if hand contains two pair
	 */
	private boolean isTwoPair(int firstPairLocation, int secondPairLocation) {

		if (firstPairLocation != -1 && secondPairLocation != -1) {
			return true;
		}

		return false;
	}
	
	/**
	 * Identify if these five cards are a straight (5 consecutive cards by rank)
	 * 
	 * @param ranks
	 *            13 element array with total # of each card in each element.
	 *            ranks[0] = # of 2s, ranks[12] = # of Aces
	 * @return array position of highest card in the straight or -1 if not a
	 *         straight.
	 * 
	 */
	private int findStraight(int[] ranks) {
		for (int first = 0; first < 9; first++) // 0--8 is 9 values, correspond
												// w/ 2 --10. 10 J Q K A is
												// highest straight
		{
			if ((ranks[first] == 1) && (ranks[first + 1] == 1) && (ranks[first + 2] == 1) && (ranks[first + 3] == 1)
					&& (ranks[first + 4] == 1))
				return first + 4; // return bin for highest value in straight

		}
		// checking for straight w/ ace as low card
		// A 2 3 4 5 all equal one
		if ((ranks[12] == 1) && (ranks[0] == 1) && (ranks[1] == 1) && (ranks[2] == 1) && (ranks[3] == 1))
			return 3; // illustrates that 5 is the highest card. 0 = Two, 1 = Three, 2 = Four, 3 = Five

		return -1; // no straight found
	}

	/**
	 * 
	 * @param threeOfKindLoc
	 *            location of card where 3 exist in hand or -1 if 3 not found
	 * @param firstPairLoc
	 *            location of card where 2 exist in hand or -1 if pair not found
	 * @return true if there's three of a kind and a pair
	 */
	private boolean isFullHouse(int threeOfKindLoc, int firstPairLoc) {

		if (threeOfKindLoc != -1 && firstPairLoc != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Converts numerical value of hand into a descriptive String
	 * 
	 * @return String describing the hand
	 */
	public String idHand() {
		int handValue = getValue();

		int handType = handValue / 10000;
		handValue %= 10000; // . . . leaving last 4 digits only.
		int info1 = handValue / 100; // 3rd and 4th digit.
		// Used to convey information about value of this hand
		// relative to others of the same type
		// ie, pair of Kings vs. pair of Fours.
		
		int info2 = handValue % 100; // 5th and 6th digit of the original #
		// contains information necessary to break any further ties
		// NOTE: not always necessary
		

		switch (handType) {
		case 9:
			return "Royal Flush, " + SUITS[info1];
		case 8:
			Card highCard = new Card(info1);
			return "Straight Flush, " +  highCard.toString() + " High";
		case 7:
			return "Four " + RANKS[info1] + "s";
		case 6:
			return "Full House, " + RANKS[info1] + "s and " + RANKS[info2] + "s";
		case 5:
			return "Flush, " + SUITS[info1];
		case 4:
			return "Straight, " + RANKS[info1] + " High";
		case 3:
			return "Three " + RANKS[info1] + "s"; 
		case 2:
			return "Two Pair, " + RANKS[info1] + "s and " + RANKS[info2] + "s";
		case 1:
			return "Pair of " + RANKS[info1] + "s";
			
		default:
			return "High card = " + new Card(info1).toString();

		}

	}

	/**
	 * 
	 * @return 13 element array, 0 index holds count of twos, 1 holds threes . .
	 *         . 12 holds aces. NOTE need to account for Ace being high or low.
	 */
	private int[] getRankCount() {
		int[] rankCount = new int[13];
		for (Card c : myCards) {
			rankCount[c.getRankNum()]++;
		}
		return rankCount;
	}

	/**
	 * 
	 * @return 4 element array 0 holds # of hearts, 1 holds # of clubs, 2 holds
	 *         # of diamonds, 3 holds # of spades
	 */
	private int[] getSuitCount() {
		int[] suitCount = new int[4];
		for (Card c : myCards) {
			suitCount[c.getSuitNum()]++;
		}
		return suitCount;
	}

	public static void main(String[] args) {
		
		Deck d = new Deck();
		d.shuffle();
		
		
		FiveCardHand hand = new FiveCardHand(d.deal(5));
		
		System.out.println(hand);
		System.out.println(hand.idHand());
	}







}