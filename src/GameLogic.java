import javafx.scene.paint.Color;

public class GameLogic {
	
	// vars of the class
	private final static Color EMPTY = Color.TRANSPARENT;
	private final static Color PLAYER1 = Color.ORANGE;
	private final static Color PLAYER2 = Color.VIOLET;
	private final static Color PIECEOUT = Color.BLUE;
	
	public static int player1Score = 0;
	public static int player2Score = 0;
	
	/* GETTERS / SETTERS */
	public static void addScore(Color player){
		if (player == PLAYER1) player1Score++;
		if (player == PLAYER2) player2Score++;
	}
	
	public static int getScore(Color player){
		if (player == PLAYER1) return player1Score;
		if (player == PLAYER2) return player2Score;
		return 0;
	}
}