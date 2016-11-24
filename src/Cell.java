// just a test file really, this code probably needs to be in AbaloneBoard.java or even the main Abalone.java file


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Cell extends Pane {
	
	public 	Cell() {
	Polygon hex = new Polygon(200.0,10.0,
								500.0,10.0,
							    690.0,350.0,
							    500.0,690.0,
							    200.0,690.0,
							    10.0,350.0);
	hex.setStroke(Color.BLACK);
	hex.setFill(Color.BLUE);
	
	getChildren().addAll(hex);
	}
}