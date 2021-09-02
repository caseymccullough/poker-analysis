
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class PokerFX extends Application{
	
	/**
	 * A hand of Poker played graphically using JavaFX
	 * @param args
	 */
	
	final static int NUM_CARDS = 5;
	
	Deck deck;
	FiveCardHand hand1;
	FiveCardHand hand2; 
	
	CardGridPane cardGrid1;
	CardGridPane cardGrid2;
	
	Button exchangeButton;
	
	VBox vbox;
	

	
	public static void main(String[] args) {
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		deck = new Deck();
		deck.shuffle();
		
		hand1 = new FiveCardHand (deck.deal(NUM_CARDS));
		hand2 = new FiveCardHand (deck.deal(NUM_CARDS));
		
		cardGrid1 = new CardGridPane(hand1, true); // show each card 
		cardGrid2 = new CardGridPane(hand2, true); // with replace checkboxes below
		
		exchangeButton = new Button ("Exchange");
		exchangeButton.setOnAction(new ExchangeButtonHandler());
		
		vbox = new VBox (10, cardGrid1, cardGrid2, exchangeButton);
		vbox.setAlignment(Pos.CENTER);

		Scene scene = new Scene (vbox);
		scene.getStylesheets().add("style.css");
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("McPoker 2020");
		// show the window
		primaryStage.setWidth(1200);
		primaryStage.setHeight(700);
		primaryStage.show();
		
	}
	
	class ExchangeButtonHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			
			boolean[] replaceAtPos;
			
			// swap cards for hand1
			replaceAtPos = cardGrid1.replaceCardInfo();
			hand1.exchange(replaceAtPos, deck);
			// false means build card grid without checkboxes
			cardGrid1 = new CardGridPane(hand1, false); 
			cardGrid1.getStyleClass().add("cards");
			
			// swap cards for hand2
			replaceAtPos = cardGrid2.replaceCardInfo();
			hand2.exchange(replaceAtPos, deck);
			// build card grid without checkboxes
			cardGrid2 = new CardGridPane(hand2, false); 
			cardGrid2.getStyleClass().add("cards");

			// identify value of final hand
			String hand1String = hand1.idHand();
			String hand2String = hand2.idHand();
			
			// Strings need to be stored in Labels to be placed in a container
			Label hand1Label = new Label (hand1String);
			hand1Label.getStyleClass().add("hand-label");
			Label hand2Label = new Label (hand2String);
			hand2Label.getStyleClass().add("hand-label");
			
			VBox resultVBox = getResultVBox();
			resultVBox.setAlignment(Pos.CENTER);
			
			vbox.getChildren().setAll(resultVBox ,cardGrid1, hand1Label, cardGrid2, hand2Label);
			
			
		}
	}
	
	private VBox getResultVBox ()
	{
		String handAssessment = "";
		String winnerAssessment = "";
		
		Label handAssessmentLabel;
		Label winnerAssessmentLabel;
		VBox vbox;
		
		// get numeric value for each hand
		int hand1Score = hand1.getValue();
		int hand2Score = hand2.getValue();
		
		if (hand1Score > hand2Score)
		{
			handAssessment = hand1.idHand() + " defeats " + hand2.idHand();
			winnerAssessment = "Player 1 Wins!";
			
		}
		else
		{
			handAssessment = hand2.idHand() + " defeats " + hand1.idHand();
			winnerAssessment = "Player 2 Wins!";
			
		}
		handAssessmentLabel = new Label (handAssessment);
		winnerAssessmentLabel = new Label (winnerAssessment);
		
		handAssessmentLabel.getStyleClass().add("result-text");
		winnerAssessmentLabel.getStyleClass().add("result-text");
		vbox = new VBox (handAssessmentLabel, winnerAssessmentLabel);
		
		System.out.println();
		
		return vbox;
	}


}
