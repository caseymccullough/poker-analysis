/**
 * Poker.java

 *
 * @author 
 * @version 1.00 2007/10/2
 */
 
 import java.util.Scanner;
 import java.util.ArrayList;
 
public class Poker {
    
    public static void main(String[] args) {
     
     Scanner sc = new Scanner (System.in);
     Deck theDeck = new Deck();
     boolean [] replace = new boolean [5];
     ArrayList <Card> discards = new ArrayList <Card>();
     
     System.out.println (theDeck);
     theDeck.shuffle();
     System.out.println ("Shuffled: ");
     System.out.println (theDeck);
     
     FiveCardHand playerHand = new FiveCardHand(theDeck.deal(5));
     FiveCardHand houseHand = new FiveCardHand (theDeck.deal(5));
     
     System.out.println ("Player's Hand: " + playerHand);
     
     char yorn; 
     for (int i = 1; i <= 5; i++)
     {
          System.out.print ("\nWould you like to replace card # " + i + " ? (y or n)");
          yorn = sc.next().charAt(0);
          if (yorn == 'y' || yorn == 'Y')
            replace[i - 1] = true;  // boolean has default value of F, so no further code needed!
       
     }
     
     System.out.print ("Replacing cards: ");
     
     for (int i = 0; i < 5; i++)
       if (replace [i] == true)
         System.out.print(i + 1 + " ");
       
     discards = playerHand.exchange (replace, theDeck);
     
     
     System.out.println ("\n\nPlayer's Hand: " + playerHand);
     
     //  based on input from user boolean values are filled in for replace[]
     //  
     
     System.out.println ("\n\nHouse Hand: " + houseHand);
     
     System.out.println ("\nThe deck: \n" + theDeck);
     
     theDeck.unshuffle();
     
      System.out.println ("\nThe deck, sorted: \n" + theDeck);
      System.out.println ("\nDiscards: " + discards);
     
     
     
     
    }
}
