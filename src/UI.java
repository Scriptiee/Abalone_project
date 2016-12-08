import javafx.scene.paint.Color;

// Perhaps use this class for implementing UI
public class UI {
	
	// vars of the class
	private final static Color PLAYER1 = Color.WHITE;
	private final static Color PLAYER2 = Color.BLACK;
	
	public static void UpdateScore(){
		// PRINTING FOR DEBUG TODO
		System.out.println("\nScore\nPlayer 1: "+GameLogic.getScore(PLAYER1)+"\nPlayer 2: "+GameLogic.getScore(PLAYER2));
	}
}
