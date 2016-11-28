import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AbaloneBoard extends Pane {
	
	// position and size of master board
	double[] hexPoints = new double[] {
			200.0,0.0, // top left
			500.0,0.0, // top right
			690.0,350.0,// middle right
			500.0,690.0,// bottom right
			200.0,690.0,// bottom left
			0.0,350.0  // middle left
	};
	
	// Board should create master board & add all Cell and Piece to it TODO
	public AbaloneBoard(){
		Polygon hex = new Polygon(hexPoints);
		hex.setStroke(Color.BLACK);
		hex.setFill(Color.BLUE);

		getChildren().addAll(hex);
	}
}

/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * resize method
 * reset game method
*/