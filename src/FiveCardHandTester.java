import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FiveCardHandTester {

	public static FiveCardHand[] hands = new FiveCardHand[10];

	@Test
	public void testHighCard() {

		FiveCardHand h = new FiveCardHand(0, 47, 4, 8, 12); // Nothing
		System.out.println(h);
		assertEquals("High card", 47, h.getValue());
		assertEquals("String for High Card", "High card = King of Spades", h.idHand());
		
		FiveCardHand h2 = new FiveCardHand(0, 4, 8, 13, 34);// 34 is 10 of Diamonds 
		System.out.println(h2);
		assertEquals("High card", 34, h2.getValue());
		assertEquals("String for High card", "High card = Ten of Diamonds", h2.idHand());

	}

	@Test
	public void testPair() {

		hands[0] = new FiveCardHand(0, 1, 4, 8, 12); // Pair of 2s
		System.out.println(hands[0]);
		assertEquals("value for pair", 10000, hands[0].getValue());
		assertEquals("String for pair", "Pair of Twos", hands[0].idHand());
		
		FiveCardHand pairOfKings = new FiveCardHand(45, 46, 4, 8, 12); // Pair of 2s
		System.out.println(pairOfKings);
		assertEquals("value for pair", 11100, pairOfKings.getValue());
		assertEquals("String for pair", "Pair of Kings", pairOfKings.idHand());
		
		FiveCardHand pairOfAces = new FiveCardHand(49, 50, 4, 8, 12); // Pair of As
		System.out.println(pairOfAces);
		assertEquals("value for pair", 11200, pairOfAces.getValue());
		assertEquals("String for pair", "Pair of Aces", pairOfAces.idHand());


	}

	@Test
	public void testTwoPair() {

		hands[1] = new FiveCardHand(0, 1, 4, 5, 10);// Two Pair 2s and 3s
		System.out.println(hands[1]);
		assertEquals("Value for Two Pair", 20100, hands[1].getValue());
		assertEquals("String for Two Pair", "Two Pair, Threes and Twos", hands[1].idHand());
		
		FiveCardHand jacksAndFours = new FiveCardHand(8, 36, 38, 0, 11);// Two Pair J and 4
		System.out.println(jacksAndFours);
		assertEquals("Value for Two Pair", 20902, jacksAndFours.getValue());
		assertEquals("String for Two Pair", "Two Pair, Jacks and Fours", jacksAndFours.idHand());
	}

	@Test
	public void testThreeOfKind() {

		FiveCardHand threeTwos = new FiveCardHand(0, 1, 2, 18, 32); // Three of a Kind 2s
		System.out.println(threeTwos);
		assertEquals("Value for Three of Kind", 30000, threeTwos.getValue());
		assertEquals("String for Three of Kind", "Three Twos", threeTwos.idHand());
		
		FiveCardHand threeAces = new FiveCardHand(48, 49, 4, 19, 51); // Three of a Kind 2s
		System.out.println(threeAces);
		assertEquals("Value for Three of Kind", 31200, threeAces.getValue());
		assertEquals("String for Three of Kind", "Three Aces", threeAces.idHand());
	}

	@Test
	public void testFullHouse() {

		FiveCardHand houseSevenJack = new FiveCardHand(21, 37, 38, 23, 22);// Full House 7 and J
		System.out.println(houseSevenJack);
		assertEquals("Value for Full House", 60509, houseSevenJack.getValue());
		assertEquals("String for Full House", "Full House, Sevens and Jacks", houseSevenJack.idHand());
		
		FiveCardHand houseAceKing = new FiveCardHand(47, 51, 50, 48, 46);// Full House A and K
		System.out.println(houseAceKing);
		assertEquals("Value for Full House", 61211, houseAceKing.getValue());
		assertEquals("String for Full House", "Full House, Aces and Kings", houseAceKing.idHand());
	}

	@Test
	public void testFourOfKind() {
		FiveCardHand fourAces = new FiveCardHand(49, 51, 46, 48, 50);// 
		System.out.println(fourAces);
		assertEquals("Value for Four of Kind", 71200, fourAces.getValue());
		assertEquals("String for Four of Kind", fourAces.idHand(), "Four Aces");
	}
	
	@Test
	public void testStraight(){
		FiveCardHand straightHand = new FiveCardHand (0, 5, 8, 12, 16);
		System.out.println (straightHand);
		assertEquals("Value for Straight", 40400, straightHand.getValue());
		assertEquals("String for Straight", straightHand.idHand(), "Straight, Six High");
	}
	
	@Test
	public void testAceLowStraight(){
		FiveCardHand aceLowStraight = new FiveCardHand (0, 5, 50, 12, 9);
		System.out.println (aceLowStraight);
		assertEquals("Value for Straight", 40300, aceLowStraight.getValue());
		assertEquals("String for Straight", aceLowStraight.idHand(), "Straight, Five High");
	}
	
	@Test
	public void testHighStraight(){
		FiveCardHand aceHighStraight = new FiveCardHand (51, 47, 43, 38, 34);
		System.out.println (aceHighStraight);
		assertEquals("Value for Straight", 41200, aceHighStraight.getValue());
		assertEquals("String for Straight", aceHighStraight.idHand(), "Straight, Ace High");
	}
	
	@Test
	public void testFlush(){ 
		FiveCardHand flushHearts = new FiveCardHand (0, 8, 16, 24, 32);
		System.out.println (flushHearts);
		assertEquals("Value for Flush", 50000, flushHearts.getValue());
		assertEquals("String for Flush", "Flush, Hearts", flushHearts.idHand());
		
		FiveCardHand flushDiamonds = new FiveCardHand (10, 34, 18, 6, 42);
		System.out.println (flushDiamonds);
		assertEquals("Value for Flush", 50200, flushDiamonds.getValue());
		assertEquals("String for Flush", "Flush, Diamonds", flushDiamonds.idHand());
	}
	
	@Test
	public void testStraightFlush(){
		FiveCardHand tenHeartsStraightFlush = new FiveCardHand (20, 24, 28, 36, 32);
		System.out.println (tenHeartsStraightFlush);
		assertEquals("Value for Straight Flush", 83200, tenHeartsStraightFlush.getValue());
		assertEquals("String for Straight Flush", "Straight Flush, Ten of Hearts High", tenHeartsStraightFlush.idHand());
	}
	
	@Test
	public void testRoyalFlush(){
		FiveCardHand heartRoyalFlush = new FiveCardHand (32, 36, 40, 44, 48);
		System.out.println (heartRoyalFlush);
		assertEquals("Value for Royal Flush", 90000, heartRoyalFlush.getValue());
		assertEquals("String for Royal Flush", "Royal Flush, Hearts", heartRoyalFlush.idHand());
		
		FiveCardHand spadeRoyalFlush = new FiveCardHand (43, 39, 51, 47, 35);
		System.out.println (spadeRoyalFlush);
		assertEquals("Value for Royal Flush", 90300, spadeRoyalFlush.getValue());
		assertEquals("String for Royal Flush", "Royal Flush, Spades", spadeRoyalFlush.idHand());
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
