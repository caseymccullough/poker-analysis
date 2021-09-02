package myapp;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CardGridPane extends GridPane {

	private static final int NUM_CARDS = 5;

	Button exchangeButton;
	CheckBox[] replaceCheckBox;
	
	public CardGridPane(FiveCardHand hand, boolean showCheckBox) {

		super();
		Label[] cardLabels = new Label[NUM_CARDS];
		Image[] cardImages = new Image[NUM_CARDS];
		ImageView[] cardImageView = new ImageView[NUM_CARDS];
		replaceCheckBox = new CheckBox[NUM_CARDS];
		
		ArrayList<Card> cards = hand.getCards();
		int numCards = cards.size();

		this.setHgap(10);
		this.setVgap(10);
		this.setAlignment(Pos.CENTER);

		this.setPadding(new Insets(10));

		for (int i = 0; i < cardLabels.length; i++) {

			String cardString = "";
			cardLabels[i] = new Label("Card # " + (i + 1));
			
			cardLabels[i].setAlignment(Pos.CENTER);
			cardLabels[i].getStyleClass().add("card-label");
			
			this.add(cardLabels[i], i, 0);

			if (i < numCards){
				cardString = "file:" + cards.get(i).imageString();
			}
			else
			{
				cardString = "file:img/back_card.png";
			}
			
			System.out.println(cardString);
			cardImages[i] = new Image(cardString);
			cardImageView[i] = new ImageView(cardImages[i]);
			cardImageView[i].setPreserveRatio(true);
			cardImageView[i].setFitWidth(120);

			this.add(cardImageView[i], i, 1);

			if (showCheckBox)
			{
				replaceCheckBox[i] = new CheckBox("Replace?");
				replaceCheckBox[i].setAlignment(Pos.CENTER);
				this.add(replaceCheckBox[i], i, 2);
			}

			

		}
	
	}
	
	/**
	 * Evaluate the array of check boxes
	 * @return boolean result indicating whether each box has been checked. 
	 */
	
	public boolean[] replaceCardInfo()
	{
		boolean [] replace = new boolean [NUM_CARDS];
		for (int i = 0; i < replace.length; i++)
		{
			replace[i] = replaceCheckBox[i].isSelected();	
		}
		return replace;
			
	}



}


