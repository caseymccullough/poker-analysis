
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
public class BarChartGenerator {
   
	
	final static String TITLE = "Results";
	final static String [] HAND_NAMES = {"High Card", "Pair", "Two Pair", 
			"Three of a Kind", "Straight", "Flush",
			"Full House", "Four of a Kind", "Straight Flush",
			"Royal Flush"};
    
	final static int HIGH_CARD = 0;
	final static int PAIR = 1;
	final static int TWO_PAIR = 2;
	final static int THREE_OF_KIND = 3;
	final static int STRAIGHT = 4;
	final static int FLUSH = 5;
	final static int FULL_HOUSE = 6;
	final static int FOUR_OF_KIND = 7;
	final static int STRAIGHT_FLUSH = 8;
	final static int ROYAL_FLUSH = 9;

	public BarChartGenerator() {
		

	}
	
	public static BarChart <Number, String> generateBarChart(double[] hands1, double[] hands2)
	{
		   NumberAxis xAxis = new NumberAxis();
	       CategoryAxis yAxis = new CategoryAxis();
	
	
        BarChart<Number,String> bc = 
            new BarChart<Number,String>(xAxis,yAxis);
        bc.setTitle(TITLE);
        bc.setVerticalGridLinesVisible(true);
        
        xAxis.setLabel("Percent Likelihood");  
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Hand Type");        
 
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        
        series1.setName("Option 1");
        series2.setName("Option 2");
        
        for (int j = 0; j < HAND_NAMES.length; j++)
        {
        		series1.getData().add(new XYChart.Data(hands1[j], HAND_NAMES[j]));
        		series2.getData().add(new XYChart.Data(hands2[j], HAND_NAMES[j]));
        }

        bc.getData().add(series1);
        bc.getData().add(series2);
        return bc;

    }
 

}