import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Piece extends Group{
	// private fields of the class
	private Ellipse piece; // for rendering the pieces
	
	// constructor for the class
	public Piece(Color player) {
		// single piece across the board just separate colours? needs implementing 
		piece = new Ellipse();
		piece.setFill(player);
	}
}

/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * resize method
 * finish constructor method
*/