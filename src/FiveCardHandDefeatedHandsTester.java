import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FiveCardHandDefeatedHandsTester {

	public static FiveCardHand[] hands = new FiveCardHand[10];

	public static void main (String[] args)
	{

		FiveCardHand h = new FiveCardHand(0, 47, 4, 8, 12); // Nothing
		printHandAssessment(h);
		
		FiveCardHand h2 = new FiveCardHand(0, 4, 8, 13, 34);// 34 is 10 of Diamonds 
		printHandAssessment (h2);
		
		hands[0] = new FiveCardHand(0, 1, 4, 8, 12); // Pair of 2s
		printHandAssessment (hands[0]);
	
		FiveCardHand pairOfKings = new FiveCardHand(45, 46, 4, 8, 12); // Pair of Kings
		printHandAssessment (pairOfKings);
	  
		
		FiveCardHand pairOfAces = new FiveCardHand(49, 50, 4, 8, 12); // Pair of As
		printHandAssessment(pairOfAces);
		
		hands[1] = new FiveCardHand(0, 1, 4, 5, 10);// Two Pair 2s and 3s
		printHandAssessment(hands[1]);
		
		FiveCardHand jacksAndFours = new FiveCardHand(8, 36, 38, 0, 11);// Two Pair J and 4
		printHandAssessment(jacksAndFours);
		/*

		FiveCardHand threeTwos = new FiveCardHand(0, 1, 2, 18, 32); // Three of a Kind 2s
		System.out.println(threeTwos);
		
		FiveCardHand threeAces = new FiveCardHand(48, 49, 4, 19, 51); // Three of a Kind 2s
		System.out.println(threeAces);

		FiveCardHand houseSevenJack = new FiveCardHand(21, 37, 38, 23, 22);// Full House 7 and J
		System.out.println(houseSevenJack);
		
		FiveCardHand houseAceKing = new FiveCardHand(47, 51, 50, 48, 46);// Full House A and K
		System.out.println(houseAceKing);

		FiveCardHand fourAces = new FiveCardHand(49, 51, 46, 48, 50);// 
		System.out.println(fourAces);

		FiveCardHand straightHand = new FiveCardHand (0, 5, 8, 12, 16);
		System.out.println (straightHand);

		FiveCardHand aceLowStraight = new FiveCardHand (0, 5, 50, 12, 9);
		System.out.println (aceLowStraight);

	
		FiveCardHand aceHighStraight = new FiveCardHand (51, 47, 43, 38, 34);
		System.out.println (aceHighStraight);
	
		FiveCardHand flushHearts = new FiveCardHand (0, 8, 16, 24, 32);
		System.out.println (flushHearts);
		
		FiveCardHand flushDiamonds = new FiveCardHand (10, 34, 18, 6, 42);
		System.out.println (flushDiamonds);
	
	
		FiveCardHand tenHeartsStraightFlush = new FiveCardHand (20, 24, 28, 36, 32);
		System.out.println (tenHeartsStraightFlush);
	
	
		FiveCardHand heartRoyalFlush = new FiveCardHand (32, 36, 40, 44, 48);
		System.out.println (heartRoyalFlush);
		
		FiveCardHand spadeRoyalFlush = new FiveCardHand (43, 39, 51, 47, 35);
		System.out.println (spadeRoyalFlush);
	*/
	}

	public static void printHandAssessment (FiveCardHand h)
	{
		//System.out.println(h);
		String result = h.idHand();
		System.out.print(result + " ");
		System.out.print(h.getValue() + " ---> ");
		System.out.println (InferiorHandCalculator.getInferiorHandCount(h.getValue()));
		System.out.println();
	}
	/*
	 * hands[3] = new FiveCardHand (10, 12, 18, 22, 26); // Straight starting w/
	 * 4
	 * 
	 * hands[4] = new FiveCardHand (12, 20, 28, 32, 36); // Flush--Hearts
	 * 
	 * 
	 * 
	 * 
	 * 
	 * hands[7] = new FiveCardHand(10, 14, 18, 22, 26); // Straight flush
	 * Diamonds start w/ 4
	 * 
	 * hands[8] = new FiveCardHand (35, 39, 43, 47, 51); // Royal Flush
	 * 
	 * 
	 */

	/**
	 * 
	 * @param h
	 *            the incoming hand
	 * @param val
	 *            the score of the hand
	 * @param s
	 *            the string equivalent of the hand
	 */

	private void testSingleHand(FiveCardHand h, int val, String s) {
		System.out.println(h);
		assertEquals(h.getValue(), val);
		assertEquals(h.idHand(), s);

	}

}
