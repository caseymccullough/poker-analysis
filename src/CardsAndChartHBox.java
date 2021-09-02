import javafx.scene.chart.BarChart;
import javafx.scene.layout.HBox;

public class CardsAndChartHBox extends HBox {

	private boolean[] hand1Replace;
	private boolean[] hand2Replace;
	
	private Deck deck;
	private FiveCardHand hand1;
	private FiveCardHand hand2;
	
	public CardsAndChartHBox (Deck deckIn, FiveCardHand hand1In, FiveCardHand hand2In){
		
		super();
		deck = deckIn;
		hand1 = hand1In;
		hand2 = hand2In;
		
		
	}
	
	

	
	
}
