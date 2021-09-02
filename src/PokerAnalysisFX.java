
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
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


public class PokerAnalysisFX extends Application{
	
	/**
	 * A hand of Poker played graphically using JavaFX
	 * @param args
	 */
	
	final static int NUM_CARDS = 5;
	final static int POSSIBLE_HANDS = 2598960; // all possible combinations of 5 cards
	
	
	Scene scene1, scene2;
	
	Deck deck;
	FiveCardHand hand1;
	FiveCardHand hand2; 
	
	CardGridPane cardGrid1;
	CardGridPane cardGrid2;
	
	Button evaluateButton;
	
	VBox vbox;
	

	
	public static void main(String[] args) {
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// create scenario #1
		deck = new Deck();
		deck.shuffle();
		ArrayList<Card> originalCards = deck.deal(5);
		hand1 = new FiveCardHand (originalCards);
		
		// create scenario #2, exact replica of scenario #1
		ArrayList<Card> originalCardCopy = (ArrayList<Card>)originalCards.clone();
		hand2 = new FiveCardHand (originalCardCopy);
		
		// each grid shows all 5 cards horizontally
		cardGrid1 = new CardGridPane(hand1, true); // show each card 
		cardGrid2 = new CardGridPane(hand2, true); // with replace checkboxes below
		
		// clicking this will lock in the checkboxes--
		// all checked will be replaced. 
		evaluateButton = new Button ("Evaluate");
		evaluateButton.setOnAction(e -> this.buildNewScene(primaryStage));
		
		// display the two hands
		vbox = new VBox (10, cardGrid1, cardGrid2, evaluateButton);
		vbox.setAlignment(Pos.CENTER);
		scene1 = new Scene (vbox);
		scene1.getStylesheets().add("style.css");

		
		primaryStage.setScene(scene1);
		primaryStage.setTitle("Poker Analysis 2020");
		// show the window
		primaryStage.setWidth(1400);
		primaryStage.setHeight(800);
		primaryStage.show();
		
	}
	
	/**
	 * build scene with new replacement cards
	 * @param primaryStage the stage being presented
	 */
	private void buildNewScene(Stage primaryStage) {
		
		// swap cards for hand1
		boolean [] hand1Replace = cardGrid1.replaceCardInfo();
		boolean [] hand2Replace = cardGrid2.replaceCardInfo();
		
		// remove cards that are marked. This will leave hand short of 5 cards. 	
		hand1.remove(hand1Replace);
		hand2.remove(hand2Replace);
		
		// get count  of each hand result
		int[] hand1Results = HandStatsGenerator.getHandCounts(deck, hand1);
		double hand1Average = HandStatsGenerator.getAverageInferiorHands();
		double hand1DefeatsPercentage = hand1Average / POSSIBLE_HANDS;
		
		
		Label hand1DefeatsLabel = getHandDefeatsLabel(hand1DefeatsPercentage);
		
		System.out.println("percentage 1: " + hand1DefeatsPercentage);
		
		int[] hand2Results = HandStatsGenerator.getHandCounts(deck, hand2);
		double hand2Average = HandStatsGenerator.getAverageInferiorHands();
		double hand2DefeatsPercentage = hand2Average / POSSIBLE_HANDS;
		
		Label hand2DefeatsLabel = getHandDefeatsLabel(hand2DefeatsPercentage);
		
		System.out.println("percentage 2: " + hand2DefeatsPercentage);
		
		// and convert to percentages.
		double[] hand1ResultPercentages = HandStatsGenerator.getPercentages(hand1Results);
		double[] hand2ResultPercentages = HandStatsGenerator.getPercentages(hand2Results);

		
		// generate bar chart
		BarChart bc1 = BarChartGenerator.generateBarChart(hand1ResultPercentages, hand2ResultPercentages);
		bc1.setLegendVisible(false);

		// build card grid for both hands
		// false means build card grid without checkboxes
		cardGrid1 = new CardGridPane(hand1, false); 
		cardGrid1.getStyleClass().add("cards");
		cardGrid2 = new CardGridPane(hand2, false); 
		cardGrid2.getStyleClass().add("cards");
		
		Label option1Label = new Label ("Option 1");
		Label option2Label = new Label ("Option 2");
		
		option1Label.getStyleClass().add("option-label");
		option2Label.getStyleClass().add("option-label");
		
		VBox option1Group = new VBox(option1Label, cardGrid1, hand1DefeatsLabel);
		option1Group.getStyleClass().add("option-group-1");
		
		VBox option2Group = new VBox(option2Label, cardGrid2, hand2DefeatsLabel);
		option2Group.getStyleClass().add("option-group-2");
		
		// identify winning hand and associated scores
		String resultString = getResultString(hand1DefeatsPercentage, hand2DefeatsPercentage);
		Label resultLabel = new Label (resultString);
	
		VBox leftSideScreen = new VBox (40, option1Group, option2Group, resultLabel);
		leftSideScreen.setAlignment(Pos.CENTER);

		// the right hand side of the screen
		//VBox rightSideScreen = new VBox (bc1);
		
		HBox handsAndChart = new HBox(30, leftSideScreen, bc1);
		handsAndChart.setAlignment(Pos.CENTER);
	
		Scene scene2 = new Scene(handsAndChart);
		scene2.getStylesheets().add("style.css");
		primaryStage.setScene(scene2);
	}

	
	private Label getHandDefeatsLabel(double handDefeatsDecimal) {
		
		Label percentWinLabel;
		DecimalFormat percentFormatter = new DecimalFormat ("0.0");
		double handDefeatsPercent = handDefeatsDecimal * 100.;
		String result = "Defeats " + percentFormatter.format(handDefeatsPercent) + " percent of hands";
		percentWinLabel = new Label (result);
		return percentWinLabel;
		
	}

	private String getResultString(double hand1Average, double hand2Average) {
		
		if (hand1Average > hand2Average)
		{
			return "Hand 1 defeats Hand 2"; 
		}
		if (hand2Average > hand1Average)
		{
			return "Hand 2 defeats Hand 1";
		}
		
		return "Hand 1 and Hand 2 are equivalent!";
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
		
		return vbox;
	}


}
