import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandStatsGenerator {

	final static int HAND_TYPES = 10;
	final static int CARDS_IN_DECK = 52;

	final static String[] THE_HANDS = { "High Card", "Pair", "Two Pair", "Three of a Kind", "Straight", "Flush",
			"Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

	static long sumOfInferiorHands;
	static int numPossibleHands;
	

	public static int[] getHandCounts(Deck d, FiveCardHand partialHand) {
		
		sumOfInferiorHands = 0;
		numPossibleHands = 0;
		
		int[] handCounts = new int[HAND_TYPES]; // first value for each hand
												// type is frequency, second is
												// weighted sum.
		


		List<int[]> allCardCombos = generateReplacementCardPositions(d, 5 - partialHand.size());

		numPossibleHands = allCardCombos.size();
		System.out.println("number of combos: " + numPossibleHands);

		while (allCardCombos.size() > 0) // pull each new mini array from the
											// bottom, removing it from the
											// List.
		{
			ArrayList<Card> temp5Cards = (ArrayList<Card>) partialHand.getCards().clone(); // start
																							// arraylist
																							// with
																							// cards
																							// not
																							// traded
																							// in

			int[] current = allCardCombos.remove(0); // take one set of
														// auto-generated card
														// pos #s.
			System.out.println(Arrays.toString(current));

			for (int i = 0; i < current.length; i++) {
				Card c = d.peek(current[i]); // peek returns copy of card at the
												// given position
				temp5Cards.add(c); // single card
			}
			System.out.println(temp5Cards);
			FiveCardHand tempHand = new FiveCardHand(temp5Cards);

			int handValue = tempHand.getValue();

				// *** HEre it is .  
			int index = handValue / 10000; // will leave number 0 to 9
			int handInfo = index % 10000; // strips first digit off the number.
											// Remaining 4 give information
											// about the nature of the
											// hand. What type of pair? What
											// type of straight, etc.?

			handCounts[index]++; // the count of this type of hand is
									// incremented

			int inferiorHandsToThisHand = InferiorHandCalculator.getInferiorHandCount(handValue);
			
			sumOfInferiorHands += inferiorHandsToThisHand;
			numPossibleHands++;
			
			// divide by total hands

		}
		
		return handCounts;

	}

	/**
	 * calculates the relative contribution of each item in an array to that
	 * array's total
	 * 
	 * @param nums
	 *            an array of numbers
	 * @return a set of percentages reflecting each number's contribution to the
	 *         whole.
	 */

	public static double[] getPercentages(int[] handCount) {
		double[] percentages = new double[handCount.length];
		int sum = getSum(handCount);

		for (int pos = 0; pos < handCount.length; pos++) {
			double ratio = (double) handCount[pos] / sum;
			percentages[pos] = ratio * 100.0;

		}

		return percentages;

	}

	/**
	 * 
	 * @param nums
	 *            the values to be summed
	 * @return the sum
	 */
	private static int getSum(int[] nums) {
		int total = 0;
		for (int i : nums) {
			total += i;
		}
		return total;
	}

	public static double getAverageInferiorHands()
	{
		return sumOfInferiorHands / numPossibleHands;
	}


	/**
	 * 
	 * @param deck
	 *            52 card deck with 5 cards removed
	 * @param numCards
	 *            the number of cards in each combination that is returned
	 * @return
	 * @return a 2 dimensional array, first dimension is number of
	 *         possibilities, second is the cards in that group
	 * 
	 */

	private static List<int[]> generateReplacementCardPositions(Deck deck, int cardsInGroup) {

		return CombinationsGenerator.generate(deck.size(), cardsInGroup);

	}

	/**
	 * 
	 * @param choose
	 *            the # of items in each combination we are forming
	 * @param items
	 *            the number of values from which those combinations can be
	 *            formed
	 * @return the number of combinations of a given length that can be
	 *         constructed from a set of numbers with numCount members
	 */

	public static void main(String[] args) {

		for (int count = 0; count < THE_HANDS.length; count++) {
			// System.out.println(THE_HANDS[count] + " Number of inferior hands:
			// " + getInferiorHands(count));
		}
		/*
		 * Deck deck = new Deck(); deck.shuffle(); FiveCardHand hand = new
		 * FiveCardHand (deck.deal(5));
		 * 
		 * Card removed = hand.remove(0); // remove first card in hand. Card
		 * removed2 = hand.remove(0);
		 * 
		 * 
		 * System.out.println(hand);
		 * 
		 * System.out.println("\nRemoved card: " + removed);
		 * System.out.println("\nRemoved card: " + removed2);
		 * 
		 * int[][] handCounts = getHandCounts (deck, hand); for (int i = 0; i <
		 * handCounts.length; i++) { System.out.println(THE_HANDS[i] + ": " +
		 * handCounts[0][i]); }
		 * 
		 * 
		 * //System.out.println(numCombos);
		 * 
		 */
	}

}
