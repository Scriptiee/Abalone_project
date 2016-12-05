import java.util.Arrays;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class GameLogic {

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

		// check if cell to move to is an enemy piece
		if ((AbaloneBoard.getCell(x, y).getCurrentPiece() != activeCellsColor) &&
				AbaloneBoard.getCell(x, y).getCurrentPiece() != EMPTY){
			// Set direction
			direction = getDirectionOfMovement(x,y);
			System.out.println("Direction: "+direction);
			tryPush(x,y); // if true -> tryPush
		} else {
			// Make sure at least one cell is highlighted
			if(AbaloneBoard.getLastClickedCell()!=null) {
				// method to check if all active cells can move
				if(getDirectionOfMovementAndTryMove(x, y)){
					// set all clicked cells to EMPTY
					for (int i = 0; i < allClickedCells.size(); i++) allClickedCells.get(i).setPiece(EMPTY);

					// for every clicked cell
					for (int i = 0; i < allClickedCells.size(); i++){
						// convert cell user data to x/y coords
						int[] coords = new int[2];
						coords[0] = Integer.parseInt(allClickedCells.get(i).getUserData().toString().split(",")[0]);
						coords[1] = Integer.parseInt(allClickedCells.get(i).getUserData().toString().split(",")[1]);

						// if direction = x -> set cell in that direction to piece of current cell
						if(direction==TOPLEFT) AbaloneBoard.getCell(coords[0]-1, coords[1]-1).setPiece(activeCellsColor);
						if(direction==TOPRIGHT) AbaloneBoard.getCell(coords[0]+1, coords[1]-1).setPiece(activeCellsColor);
						if(direction==RIGHT) AbaloneBoard.getCell(coords[0]+2, coords[1]).setPiece(activeCellsColor);
						if(direction==BOTTOMRIGHT) AbaloneBoard.getCell(coords[0]+1, coords[1]+1).setPiece(activeCellsColor);
						if(direction==BOTTOMLEFT) AbaloneBoard.getCell(coords[0]-1, coords[1]+1).setPiece(activeCellsColor);
						if(direction==LEFT) AbaloneBoard.getCell(coords[0]-2, coords[1]).setPiece(activeCellsColor);
					}
					// unclick all clicked cells once loop ends
					AbaloneBoard.clearAllClickedCells();
				}
			}
		}
	}

	private static int getDirectionOfMovement(int x, int y){
		allClickedCells = AbaloneBoard.getAllClickedCells(); // Store all clicked cells
		Cell moveToThisCell = AbaloneBoard.getCell(x, y); // Cell to move to

		for (int i = 0; i < allClickedCells.size(); i++){
			Cell[] cellNeighbours = allClickedCells.get(i).getAllNeighbours(); // Store all neighbours of currently selected (i) cell
			for (int j = 0; j < cellNeighbours.length; j++){
				// (get direction) if true -> cell to move to is a neighbour of clicked cell -> so j is current direction
				if(cellNeighbours[j] == moveToThisCell){
					// for every active cell & direction, check if all cells can move in that direction
					return j;
				}
			}
		}
		return 7;
	}

	// this method gets an available direction of one cell, and passes that direction to checkCanAllCellsMoveInDirection(_)
	private static boolean getDirectionOfMovementAndTryMove(int x, int y){

		allClickedCells = AbaloneBoard.getAllClickedCells(); // Store all clicked cells
		Cell moveToThisCell = AbaloneBoard.getCell(x, y); // Cell to move to

		for (int i = 0; i < allClickedCells.size(); i++){
			Cell[] cellNeighbours = allClickedCells.get(i).getAllNeighbours(); // Store all neighbours of currently selected (i) cell
			for (int j = 0; j < cellNeighbours.length; j++){
				// (get direction) if true -> cell to move to is a neighbour of clicked cell -> so j is current direction
				if(cellNeighbours[j] == moveToThisCell){
					// for every active cell & direction, check if all cells can move in that direction
					if(checkCanAllCellsMoveInDirection(allClickedCells, j)) return true;
				}
			}
		}
		return false;
	}

	// helper method for canAllActiveCellsMove() -- checks can all active cells move in the given direction
	private static boolean checkCanAllCellsMoveInDirection(ArrayList<Cell> allClickedCells, int dir){
		// set direction
		direction = dir;
		// counter used to determine return statement
		int counter = 0;
		for (int i = 0; i < allClickedCells.size(); i++){
			// Store all neighbours
			Cell[] cellNeighbours = allClickedCells.get(i).getAllNeighbours();
			// if neighbour cell in a given direction is empty OR is an active piece, counter++
			if ((cellNeighbours[direction].getCurrentPiece() == EMPTY) || 
					cellNeighbours[direction].isClicked){
				counter++;
			}
		}
		// if counter equals size of all clicked cells return true (all can move)
		if (counter == allClickedCells.size()) return true;
		return false;
	}

	// PUSH ENEMY METHOD
	private static void tryPush(int x, int y){
		// Store "weight" of enemy
		int enemyWeight = 0;
		// Store "weight" of player
		int playerWeight = allClickedCells.size();

		System.out.println("Player Weight: "+playerWeight);

		Cell[] enemyCellNeighbours = new Cell[6];
		// for every enemy piece in a direction, add to counter
		outerloop:
			for (int i = 0; i < allClickedCells.size(); i++){
				enemyCellNeighbours = allClickedCells.get(i).getAllNeighbours();
				for (int j = 0; j < enemyCellNeighbours.length; j++){
					if (enemyCellNeighbours[j] == AbaloneBoard.getCell(x, y)){
						break outerloop;
					}
				}
			}

		// FOR EVERY PIECE IN DIRECTION THAT IS AN ENEMY -> ADD TO ENEMY WEIGHT
		if(enemyCellNeighbours[direction].getCurrentPiece() == enemyCellsColor){
			enemyWeight = 1;
			allEnemyCells.add(enemyCellNeighbours[direction]);

			enemyCellNeighbours = enemyCellNeighbours[direction].getAllNeighbours();
			if(enemyCellNeighbours[direction].getCurrentPiece() == enemyCellsColor){
				enemyWeight = 2;
				allEnemyCells.add(enemyCellNeighbours[direction]);

				enemyCellNeighbours = enemyCellNeighbours[direction].getAllNeighbours();
				if(enemyCellNeighbours[direction].getCurrentPiece() == enemyCellsColor){
					enemyWeight = 3;
					allEnemyCells.add(enemyCellNeighbours[direction]);

					enemyCellNeighbours = enemyCellNeighbours[direction].getAllNeighbours();
					if(enemyCellNeighbours[direction].getCurrentPiece() == enemyCellsColor){
						enemyWeight = 4;
						allEnemyCells.add(enemyCellNeighbours[direction]);
					}
				}
			}
		}

		System.out.println("Enemy Weight: "+enemyWeight+"\n");

		// IF PLAYER IS BIGGER -> MOVE ALL PIECES
		if (playerWeight > enemyWeight){
			for (int i = 0; i < allClickedCells.size(); i++) allClickedCells.get(i).setPiece(EMPTY);
			for (int i = 0; i < allEnemyCells.size(); i++) allEnemyCells.get(i).setPiece(EMPTY);

			// MOVE EVERY PLAYER CELL
			for (int i = 0; i < allClickedCells.size(); i++){
				// convert cell user data to x/y coords
				int[] coords = new int[2];
				coords[0] = Integer.parseInt(allClickedCells.get(i).getUserData().toString().split(",")[0]);
				coords[1] = Integer.parseInt(allClickedCells.get(i).getUserData().toString().split(",")[1]);

				// if direction = x -> set cell in that direction to piece of current cell
				if(direction==TOPLEFT) AbaloneBoard.getCell(coords[0]-1, coords[1]-1).setPiece(activeCellsColor);
				if(direction==TOPRIGHT) AbaloneBoard.getCell(coords[0]+1, coords[1]-1).setPiece(activeCellsColor);
				if(direction==RIGHT) AbaloneBoard.getCell(coords[0]+2, coords[1]).setPiece(activeCellsColor);
				if(direction==BOTTOMRIGHT) AbaloneBoard.getCell(coords[0]+1, coords[1]+1).setPiece(activeCellsColor);
				if(direction==BOTTOMLEFT) AbaloneBoard.getCell(coords[0]-1, coords[1]+1).setPiece(activeCellsColor);
				if(direction==LEFT) AbaloneBoard.getCell(coords[0]-2, coords[1]).setPiece(activeCellsColor);
			}

			// MOVE EVERY ENEMY CELL
			for (int i = 0; i < allEnemyCells.size(); i++){
				// convert cell user data to x/y coords
				int[] coords = new int[2];
				coords[0] = Integer.parseInt(allEnemyCells.get(i).getUserData().toString().split(",")[0]);
				coords[1] = Integer.parseInt(allEnemyCells.get(i).getUserData().toString().split(",")[1]);

				// if direction = x -> set cell in that direction to piece of current cell
				if(direction==TOPLEFT) AbaloneBoard.getCell(coords[0]-1, coords[1]-1).setPiece(enemyCellsColor);
				if(direction==TOPRIGHT) AbaloneBoard.getCell(coords[0]+1, coords[1]-1).setPiece(enemyCellsColor);
				if(direction==RIGHT) AbaloneBoard.getCell(coords[0]+2, coords[1]).setPiece(enemyCellsColor);
				if(direction==BOTTOMRIGHT) AbaloneBoard.getCell(coords[0]+1, coords[1]+1).setPiece(enemyCellsColor);
				if(direction==BOTTOMLEFT) AbaloneBoard.getCell(coords[0]-1, coords[1]+1).setPiece(enemyCellsColor);
				if(direction==LEFT) AbaloneBoard.getCell(coords[0]-2, coords[1]).setPiece(enemyCellsColor);
			}
		}
		// unclick all clicked cells once loop ends
		AbaloneBoard.clearAllClickedCells();
		allClickedCells.clear();
		allEnemyCells.clear();
	}

	// Set enemyCellsColor Color
	public static void setEnemyColor(Color playerColor){
		if (playerColor == PLAYER1){
			enemyCellsColor = PLAYER2;
		} else {
			enemyCellsColor = PLAYER1;
		}
	}
}












