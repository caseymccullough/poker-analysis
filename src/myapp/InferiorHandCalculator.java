package myapp;

public class InferiorHandCalculator {

	public static final int[] HAND_FREQUENCY = {1302540, 1098240, 123552, 54912, 10200, 5108, 3744, 624, 36, 4};
	public static final int TOTAL_FREQUENCY = 2598960;
	
	
	/**
	 * for a given hand, report the # of hands that it would beat.
	 * @param index the type of hand
	 * @return the number of hands (out of 2,598,960 possible combinations) that this hand would defeat. 
	 */
	public static int getInferiorHandCount(int handScore) {
		
		// Divide out handScore into two parts. 
		
		int handType = handScore / 10000; // will leave number 0 to 9
		int handDetails = handScore % 10000; // strips first digit off the number.
										// Remaining 4 give information
										// about the nature of the
										// hand. What type of pair? What
										// type of straight, etc.?
		
		// based on the type of hand alone (assumes lowest form of this hand)
		int inferiorHandsBase = getInferiorHands(handType);
		
		System.out.print("inferior hands base: " + inferiorHandsBase + " ");
		
		switch (handType)
		{
			case FiveCardHand.HIGH_CARD:
				int highCardID = handDetails / 100;
				int highCardRank = new Card (highCardID).getRankNum();
				return inferiorHandsBase + highCardRank * HAND_FREQUENCY [FiveCardHand.HIGH_CARD] / 13;
				// note that if it's 2s, this corresponds with position 0 in the array.  
				// 0/13 means that no portion of the High Card Hands will be allotted to 2s. 
				
			case FiveCardHand.PAIR:
				int pairRank = handDetails / 100;
				return inferiorHandsBase + pairRank * HAND_FREQUENCY [FiveCardHand.PAIR] / 13;
				
			case FiveCardHand.TWO_PAIR:
				int firstPairRank = handDetails / 100;
				int secondPairRank = handDetails % 100;
				return inferiorHandsBase + firstPairRank * HAND_FREQUENCY [FiveCardHand.TWO_PAIR] / 13 
											+ secondPairRank * HAND_FREQUENCY [FiveCardHand.TWO_PAIR] / (13 * 13);
				
			case FiveCardHand.THREE_OF_KIND:
				int tripleRank = handDetails / 100;
				return inferiorHandsBase + tripleRank * HAND_FREQUENCY [FiveCardHand.THREE_OF_KIND] / 13;
				
			case FiveCardHand.STRAIGHT:
				int topCardInStraight = handDetails / 100; 
				Card topCard = new Card(topCardInStraight);
				
				// note only 10 ways to get a straight.  A--10 as low card. 
				return inferiorHandsBase + topCard.getRankNum() * HAND_FREQUENCY[FiveCardHand.STRAIGHT] / 10 + topCard.getSuitNum();
			
			case FiveCardHand.FLUSH: 
				int topCardIdFlush = handDetails / 100; 
				Card topCardFlush = new Card(topCardIdFlush);
			
				return inferiorHandsBase + topCardFlush.getRankNum() * HAND_FREQUENCY[FiveCardHand.FLUSH] / 13 + topCardFlush.getSuitNum();
			
			case FiveCardHand.FULL_HOUSE:
				int rankOfThree = handDetails /100;
				int rankOfTwo = handDetails % 100; // the last two digits of the # (secondary card)

				// one thirteenth of full house hands will be defeated for each increase in rank of the 3 of a kind 
				// within each 1/13, 1/13 of these will be defeated for each increase in rank of the 2 of a kind. 
				
				return inferiorHandsBase + rankOfThree * HAND_FREQUENCY [FiveCardHand.FULL_HOUSE] / 13 + 
							rankOfTwo * HAND_FREQUENCY[FiveCardHand.FULL_HOUSE] / (13 * 13);
				
			case FiveCardHand.FOUR_OF_KIND:
				int groupOfFourId = handDetails / 100;
				
				int rankOfFourOfKind = new Card(groupOfFourId).getRankNum();
				
				return inferiorHandsBase + rankOfFourOfKind * HAND_FREQUENCY[FiveCardHand.FOUR_OF_KIND] / 13;
				
			case FiveCardHand.STRAIGHT_FLUSH:
				
				int topCardIdStraightFlush = handDetails / 100;
				Card highCard = new Card(topCardIdStraightFlush);
				
				return inferiorHandsBase + highCard.getRankNum() * HAND_FREQUENCY[FiveCardHand.STRAIGHT_FLUSH] / 10 + highCard.getSuitNum();
				
			case FiveCardHand.ROYAL_FLUSH:
				
				int topCardIdRoyalFlush = handDetails / 100;
				Card topCardRoyalFlush = new Card(topCardIdRoyalFlush);
				
				return inferiorHandsBase + topCardRoyalFlush.getSuitNum();
				
			default: 
				return - 1; // error. 
			
		}
	
	}
	
	/**
	 * 
	 * @param index the number indicated the type of hand 
	 * @return the total number of possible hands that this category of hand will defeat. 
	 */

	private static int getInferiorHands(int index) {
		
		int total = 0;
		
		for (int pos = 0; pos < index; pos++)
		{
			total += HAND_FREQUENCY[pos];
		}
		
		return total;

	}

	private static double percentWin (int handsDefeats)
	{
		return (double) handsDefeats / TOTAL_FREQUENCY;
	}
	
	
	public static void main(String[] args) {
		
		for (int i = FiveCardHand.HIGH_CARD; i <= FiveCardHand.ROYAL_FLUSH; i++)
		{
			int numDefeats = getInferiorHands(i);
			System.out.println(i + " defeats " + numDefeats + " hands : Percent win = " + percentWin(numDefeats));
		}

		int inferiorCount = getInferiorHandCount(5150);
		System.out.println();
		System.out.println(inferiorCount + " " + percentWin(inferiorCount));
		
		int inferiorCount2 = getInferiorHandCount(11200);
		System.out.println();
		System.out.println(inferiorCount2 + " " + percentWin(inferiorCount2));
	}

}
