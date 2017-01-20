import javafx.scene.paint.Color;

public class GameLogic {
	
	// vars of the class
	private final static Color PLAYER1 = Color.ORANGE;
	private final static Color PLAYER2 = Color.VIOLET;
	private static Color CURRENT_PLAYER;
	
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
	 
	public static void setCurrentPlayer(Color player){
		CURRENT_PLAYER = player;
	}
	
	public static void changeCurrentPlayer(){
		if(CURRENT_PLAYER == PLAYER1)CURRENT_PLAYER = PLAYER2;
		else if(CURRENT_PLAYER == PLAYER2)CURRENT_PLAYER = PLAYER1;
	}
	
	public static Color getCurrentPlayer(){
		return CURRENT_PLAYER;
	}
}