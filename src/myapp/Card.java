package myapp;

public class Card implements Comparable {
	
	private static final String[] RANKS = { "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
			"Jack", "Queen", "King", "Ace" };

	private static final String[] SUITS = { "Diamonds", "Clubs", "Hearts", "Spades" };
	
	public static final String [] IMAGE_RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10",
			"jack", "queen", "king", "ace"};

	public static final String [] IMAGE_SUITS = {"diamonds", "clubs", "hearts", "spades"};

	private int myID;
	private int mySuit;
	private int myRank;

	public Card(int val)
	// val is between 0 and 51 inclusive
	{
		myID = val;
		mySuit = val % 4; // 0 to 3
		myRank = val / 4; // 0 to 12
	}

	public String toString() {
		String s = getRank() + " of " + getSuit();
		return s;
	}

	public int getRankNum() {
		return myRank;
	}

	public int getSuitNum() {
		return mySuit;
	}

	public String getRank() {
		return RANKS[myRank];
	}

	public String getSuit() {
		return SUITS[mySuit];
	}
	
	public int getID()
	{
		return myID;
	}
	
	public String imageString ()
	{
		  String s = "img/";
		  s += IMAGE_RANKS[getRankNum()];
		  s += "_of_";
		  s += IMAGE_SUITS[getSuitNum()];
		  s += ".png";

		  return s; 
	}

	public int compareTo(Object obj) // we must define this to implement Comparable
	{
		Card c = (Card) obj;
		return this.getID() - c.getID();
	}

}
