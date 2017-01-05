import java.util.ArrayList;

import javafx.scene.paint.Color;

public class MovementAndPushing {

	// vars of the class
	private static int direction = 0; // 0 == TL || 1 == TR || 2 == R || 3 == BR || 4 == BL || 5 == L
	private static ArrayList<Cell> allClickedCells = new ArrayList<Cell>();
	private static Color activeCellsColor;
	private static ArrayList<Cell> allEnemyCells = new ArrayList<Cell>();
	private static Color enemyCellsColor;

	private final static Color EMPTY = Color.TRANSPARENT;
	private final static Color PLAYER1 = Color.WHITE;
	private final static Color PLAYER2 = Color.BLACK;
	private final static Color PIECEOUT = Color.BLUE;

	private final static int TOPLEFT = 0;
	private final static int TOPRIGHT = 1;
	private final static int RIGHT = 2;
	private final static int BOTTOMRIGHT = 3;
	private final static int BOTTOMLEFT = 4;
	private final static int LEFT = 5;

	// movePiece method that handles neighbours checking and the movement
	public static void movePiece(int x, int y) {
		// store all clicked cells
		allClickedCells = AbaloneBoard.getAllClickedCells();
		// active cells piece
		activeCellsColor = allClickedCells.get(0).getCurrentPiece();
		// set enemy cell color
		setEnemyColor(activeCellsColor);
		// Set direction
		direction = getDirectionOfMovement(x,y);

		// check if cell to move to is an enemy piece
		if ((AbaloneBoard.getCell(x, y).getCurrentPiece() != activeCellsColor) &&
				AbaloneBoard.getCell(x, y).getCurrentPiece() != EMPTY){
			tryPushEnemyPiece(x,y); // if true -> tryPush
		} 
		else // if cell != enemy piece
		{
			// Make sure at least one cell is highlighted
			if(AbaloneBoard.getLastClickedCell()!=null) {
				// method to check if all active cells can move
				if(checkCanAllCellsMoveInDirection()){
					// set all clicked cells to EMPTY
					for (int i = 0; i < allClickedCells.size(); i++) allClickedCells.get(i).setPiece(EMPTY);
					// for every clicked cell -> move
					for (int i = 0; i < allClickedCells.size(); i++){
						move(allClickedCells.get(i), activeCellsColor);
					}
					// unclick all clicked cells once loop ends
					AbaloneBoard.clearAllClickedCells();
				}
			}
		}
	}

	// checks can all active cells move in the given direction
	private static boolean checkCanAllCellsMoveInDirection(){
		// Ensure we have a valid direction
		if (direction <= 6){
			// counter used to determine return statement
			int counter = 0;
			for (int i = 0; i < allClickedCells.size(); i++){
				// Store all neighbours
				Cell[] cellNeighbours = allClickedCells.get(i).getAllNeighbouringCells();
				// if neighbour cell in a given direction is empty OR is an active piece, counter++
				if ((cellNeighbours[direction].getCurrentPiece() == EMPTY) || cellNeighbours[direction].isClicked){
					counter++;
				}
			}
			// if counter equals size of all clicked cells return true (all can move)
			if (counter == allClickedCells.size()) return true;
			return false;
		}
		return false;
	}

	// PUSH ENEMY METHOD
	private static void tryPushEnemyPiece(int x, int y){
		// Store "weight" of enemy
		int enemyWeight = 0;
		// Store "weight" of player
		int playerWeight = allClickedCells.size();
		// int to check how many clicked pieces are touching the enemy
		// if more than one, it is an invalid push
		int howManyClickedPiecesTouchEnemy = 0;

		Cell[] enemyCellNeighbours = new Cell[6];

		// safety to ensure only one clicked piece is next to the enemy
		for (int i = 0; i < allClickedCells.size(); i++){
			enemyCellNeighbours = allClickedCells.get(i).getAllNeighbouringCells();
			for (int j = 0; j < enemyCellNeighbours.length; j++){
				if (enemyCellNeighbours[j] == AbaloneBoard.getCell(x, y)){
					howManyClickedPiecesTouchEnemy++;
				}
			}
		}
		// find closest player piece to enemy neighbour
		outerloop:
			for (int i = 0; i < allClickedCells.size(); i++){
				enemyCellNeighbours = allClickedCells.get(i).getAllNeighbouringCells();
				for (int j = 0; j < enemyCellNeighbours.length; j++){
					if (enemyCellNeighbours[j] == AbaloneBoard.getCell(x, y)){
						break outerloop;
					}
				}
			}

		setEnemyColor(activeCellsColor);

		if (howManyClickedPiecesTouchEnemy == 1){
			// FOR EVERY PIECE IN GIVEN DIRECTION THAT IS AN ENEMY -> ADD TO ENEMY WEIGHT
			outerif:
				if(enemyCellNeighbours[direction].getCurrentPiece() == enemyCellsColor){
					enemyWeight = 1;
					allEnemyCells.add(enemyCellNeighbours[direction]);

					enemyCellNeighbours = enemyCellNeighbours[direction].getAllNeighbouringCells();	// Set new enemyCellNeighbours using next piece in the direction
					if(isPieceOut(enemyCellNeighbours[direction],false)) break outerif;					// IF that piece is PIECEOUT, leave block
					if(enemyCellNeighbours[direction].getCurrentPiece() == enemyCellsColor){		// -> IF that piece is equal to the enemy
						enemyWeight = 2;															// --> Increase enemy weight
						allEnemyCells.add(enemyCellNeighbours[direction]);							// ---> Add piece to ArrayList of all enemy cells to be moved

						enemyCellNeighbours = enemyCellNeighbours[direction].getAllNeighbouringCells();
						if(isPieceOut(enemyCellNeighbours[direction],false)) break outerif;
						if(enemyCellNeighbours[direction].getCurrentPiece() == enemyCellsColor){
							enemyWeight = 3;
							allEnemyCells.add(enemyCellNeighbours[direction]);
						}

					}
				}

		// IF PLAYER IS BIGGER -> MOVE ALL PIECES
		if (playerWeight > enemyWeight){
			// Update score if pushing out of board
			isPieceOut(enemyCellNeighbours[direction], true);
			// Remove all pieces in old position
			for (int i = 0; i < allClickedCells.size(); i++) allClickedCells.get(i).setPiece(EMPTY);
			for (int i = 0; i < allEnemyCells.size(); i++) allEnemyCells.get(i).setPiece(EMPTY);

			// Set new player positions
			for (int i = 0; i < allClickedCells.size(); i++){
				move(allClickedCells.get(i), activeCellsColor);
			}

			// Set new enemy positions
			for (int i = 0; i < allEnemyCells.size(); i++){
				move(allEnemyCells.get(i), enemyCellsColor);
			}
		}
		}
		// unclick all cells once loop ends
		AbaloneBoard.clearAllClickedCells();
		allClickedCells.clear();
		allEnemyCells.clear();
	}

	// method to actually move the cells
	private static void move(Cell cell, Color player){
		// cell position
		int[] cellPos = cell.getCellPos();

		// if direction = x -> set cell in that direction to piece of current cell
		if(direction==TOPLEFT) AbaloneBoard.getCell(cellPos[0]-1, cellPos[1]-1).setPiece(player);
		if(direction==TOPRIGHT) AbaloneBoard.getCell(cellPos[0]+1, cellPos[1]-1).setPiece(player);
		if(direction==RIGHT) AbaloneBoard.getCell(cellPos[0]+2, cellPos[1]).setPiece(player);
		if(direction==BOTTOMRIGHT) AbaloneBoard.getCell(cellPos[0]+1, cellPos[1]+1).setPiece(player);
		if(direction==BOTTOMLEFT) AbaloneBoard.getCell(cellPos[0]-1, cellPos[1]+1).setPiece(player);
		if(direction==LEFT) AbaloneBoard.getCell(cellPos[0]-2, cellPos[1]).setPiece(player);
	}

	/* GETTERS / SETTERS */
	// Set enemyCellsColor Color
	public static void setEnemyColor(Color playerColor){
		if (playerColor == PLAYER1){
			enemyCellsColor = PLAYER2;
		} else {
			enemyCellsColor = PLAYER1;
		}
	}

	// returns the direction piece will move given mouse click
	private static int getDirectionOfMovement(int x, int y){
		allClickedCells = AbaloneBoard.getAllClickedCells(); // Store all clicked cells
		Cell moveToThisCell = AbaloneBoard.getCell(x, y); // Cell to move to

		for (int i = 0; i < allClickedCells.size(); i++){
			Cell[] cellNeighbours = allClickedCells.get(i).getAllNeighbouringCells(); // Store all neighbours of currently selected (i) cell
			for (int j = 0; j < cellNeighbours.length; j++){
				// (get direction) if true -> cell to move to is a neighbour of clicked cell -> so j is current direction
				if(cellNeighbours[j] == moveToThisCell){
					// for every active cell & direction, check if all cells can move in that direction
					return j;
				}
			}
		}
		return 7; // error
	}

	// handles if the given cell is a PIECEOUT
	private static boolean isPieceOut(Cell cell, boolean shouldPushOut){
		if(cell.getCurrentPiece() == PIECEOUT) {
			if(!shouldPushOut) return true;
			else {
				GameLogic.addScore(activeCellsColor);
				UI.UpdateScore();
			}
		}
		return false;
	}
}