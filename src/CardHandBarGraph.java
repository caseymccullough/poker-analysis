import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.stage.Stage;

public class CardHandBarGraph extends Application {

    @Override public void start(Stage stage) {
        stage.setTitle("Hand Frequency Bar Chart Sample");
        int[] numHands = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        //BarChart hf = BarChartGenerator.generateBarChart(numHands);
        //Scene scene  = new Scene(hf,800,600);
        //stage.setScene(scene);
        stage.show();
        
    }
	
    public static void main(String[] args) {
        launch(args);
    }

}
