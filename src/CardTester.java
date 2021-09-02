import java.util.ArrayList;
import java.util.Random;

/**
 * CardTester.java

 *
 * @author 
 * @version 1.00 2007/10/2
 */

 
public class CardTester {
	
    public static void main(String[] args) {
    
    	/*		System.out.println("n" + "\tn / 13\tn % 13");
    	for (int i = 0; i < 52; i++)
    		System.out.println ("" + i + "\t" + i / 13 + "\t" + i % 13);
    	 */
    	
    	Random gen = new Random();
    	ArrayList<Card> theCards = new ArrayList<Card>();

    	for (int j = 0; j < 52; j++)
    		theCards.add(new Card(j));

    	ArrayList <Card> shuffled = new ArrayList <Card>();

    	for (int k = 0; k < theCards.size(); k++)
    	{
    	  Card temp = theCards.remove(k);
    	  int pos = gen.nextInt(shuffled.size() + 1);
    	  shuffled.add(temp);
    	}

    	System.out.println ("shuffled: " + shuffled);
    	System.out.println ("theCards: " + theCards);
    	
   
    	Card [] fullDeck = new Card [52];
    	
    	for (int i = 0; i < fullDeck.length; i++)
    	 	fullDeck[i] = new Card (i);
     // any value between 0 and 51 produces a legitimate card
     
    	for (int i = 0; i < fullDeck.length; i++)
    		System.out.println("Card # " + (i + 1) + ": " + fullDeck[i]);
     // implicit call to toString() 
     // should print in format 
     // "Two of Clubs"
     // "King of Hearts", etc. 
    	
    
    
    }
}
