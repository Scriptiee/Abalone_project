// just a test file really, this code probably needs to be in AbaloneBoard.java or even the main Abalone.java file 

import javafx.event.Event;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Cell extends Pane {
	
	Circle render = new Circle();
	
	public 	Cell() {
		// Create cell shape here 
		render.setFill(Color.WHITE);
		render.setStroke(Color.RED);
		render.setRadius(25);
				
		getChildren().addAll(render);

		//render.addEventFilter(Event.ANY, e -> System.out.println( e));
		
	}
}


/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * resize method
 * finish constructor method
 * getPiece and setPiece methods
*/