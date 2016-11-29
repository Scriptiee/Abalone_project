// just a test file really, this code probably needs to be in AbaloneBoard.java or even the main Abalone.java file 

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cell extends Pane {
	
	CustomControl cc_control = new CustomControl();
	Circle render = new Circle();
	
	public 	Cell() {
		// Create cell shape here 
		render.setFill(Color.RED);
		render.setRadius(50);
		
		getChildren().addAll(render);
	}
}


/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * resize method
 * finish constructor method
 * getPiece and setPiece methods
*/