import java.awt.*;
import java.awt.event.*;								// #1
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;   

/******************************************************************************
 * 
 * GraphicsCardDemo.java
 *
 * 
 *****************************************************************************/
public class GraphicsCardDemo extends JFrame
implements KeyListener		// #2
{
	// Class Scope Finals
	private static final int SCREEN_WIDTH = 1100;
	private static final int SCREEN_HEIGHT = 700;
	private static final int TOP_MARGIN = 100;
	
	private static final int BAR_HEIGHT = 22;

	private static final int CARD_WIDTH = 100;
	private static final int CARD_HEIGHT = 145;
	private static final int CARD_GAP = 10;
	
	private static final int VERTICAL_HAND_GAP = 100; 
	
	private static final int HAND_WIDTH = 5 * CARD_WIDTH + 4 * CARD_GAP;
	private static final int SIDE_MARGIN = (SCREEN_WIDTH - HAND_WIDTH) / 2;
	private static final Color TABLE_COLOR = Color.GREEN;
	private static final Font POKER_FONT = new Font("SukhumvitSet-Medium", Font.BOLD, 52);
	
	final static String TEST_FILE  = "TesterHands.txt";
	
	// Class Scope Variables
	
	
	private static ArrayList<FiveCardHand> hands;
	private static FiveCardHand hand;
	private static int handCount = 0; // how many hands have been drawn
	
	
	// Constructor 
	
	public GraphicsCardDemo()
	{
	    

	}

	// Methods
	/**
	 * Create the window and register this as a KeyListener
	 * 
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main (String[] args) throws FileNotFoundException
	{
		// Set up the JFrame window.
		GraphicsCardDemo gp = new GraphicsCardDemo();
		gp.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		gp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gp.addKeyListener(gp);		
		
		Scanner sc = new Scanner (System.in);
		hands = new ArrayList<FiveCardHand>();
		
	    	File file = new File (TEST_FILE);
	    	Scanner inputFile = new Scanner(file);
	    	ArrayList<Card> cards;
	    	
	    	while (inputFile.hasNextLine())
	    	{
	    		String fiveNums = inputFile.nextLine();
	    		System.out.println(fiveNums);
	    
		
		cards = new ArrayList<Card>();
		cards.add(new Card(inputFile.nextInt()));
		cards.add(new Card(inputFile.nextInt()));
		cards.add(new Card(inputFile.nextInt()));
		cards.add(new Card(inputFile.nextInt()));
		cards.add(new Card(inputFile.nextInt()));
		
		hand = new FiveCardHand(cards);
		hands.add(hand);
		System.out.println ("hand added");
		System.out.println(hand);
		
		cards.clear();
		
		
	    	}

	    	gp.setVisible(true);
		
		
	}

	/**
	 * Called when a key is first pressed
	 * Required for any KeyListener
	 * 
	 * @param e		Contains info about the key pressed
	 */
	public void keyPressed(KeyEvent e)					// #4A
	{
		int keyCode = e.getKeyCode();

		
		repaint();
	}

	/**
	 * Called when typing of a key is completed
	 * Required for any KeyListener
	 * 
	 * @param e		Contains info about the key typed
	 */
	public void keyTyped(KeyEvent e)					// #4B
	{
	}

	/**
	 * Called when a key is released
	 * Required for any KeyListener
	 * 
	 * @param e		Contains info about the key released
	 */
	public void keyReleased(KeyEvent e)					// #4C
	{
	}

	/**
	 * paint - draw the figure
	 * 
	 * @param g		Graphics object to draw in
	 */
	
	public void drawHand (FiveCardHand hand, Graphics g, int x, int y)
	{
		ArrayList cards = hand.getCards();
		for (int i = 0; i < cards.size(); i++)
		{
			Card c = (Card)cards.get(i);
			Image image = new ImageIcon(c.imageString()).getImage(); 
			int desiredWidth = image.getWidth(this) / 5;
			int desiredHeight = image.getHeight(this) / 5;
			g.drawImage(image,  x + i * (desiredWidth + CARD_GAP) , y,
					desiredWidth, desiredHeight,
					this);
			
		}
	}
	// drawHand(cards, g, 100, 100, 20);
	public void paint(Graphics g)
	{
		g.setColor(TABLE_COLOR);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		Deck d = new Deck();
		d.shuffle();
		
		
		FiveCardHand hand = new FiveCardHand(d.deal(5));
		
		System.out.println(hand);
		System.out.println(hand.idHand());
		
		//FiveCardHand heartRoyalFlush = new FiveCardHand (32, 36, 40, 44, 48);
		FiveCardHand jacksAndFours = new FiveCardHand(8, 36, 38, 1, 11);// Two Pair J and 4

		g.setFont(POKER_FONT);
		g.setColor(Color.WHITE);
		g.drawString("Casey's World of Poker", SIDE_MARGIN, TOP_MARGIN);
		drawHand(hand, g, SIDE_MARGIN, TOP_MARGIN + VERTICAL_HAND_GAP);
		drawHand(jacksAndFours, g, SIDE_MARGIN, TOP_MARGIN + CARD_HEIGHT + VERTICAL_HAND_GAP * 2);
	
		
	
	


	}
}

