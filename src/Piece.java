import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece extends Group{
	// private fields of the class
	private Circle piece; // for rendering the pieces
	private Color playerColour;
	
	// constructor for the class
	public Piece(Color player) {
		playerColour = player;
		piece = new Circle(25);
		piece.setFill(playerColour);
		getChildren().add(piece);
	}
	
	// returns this pieces colour
	public Color getPiece(){
		return playerColour;
	}
}

/* -----STILL NEEDS----- TODO
 * (take a comment and work on it)
 * finish constructor method
*/