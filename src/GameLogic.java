import java.util.Arrays;
import java.util.ArrayList;

import javafx.scene.paint.Color;

public class GameLogic {

	private final static Color EMPTY = Color.TRANSPARENT;
	private static int direction = 0;
	private static ArrayList<Cell> allClickedCells = new ArrayList<Cell>();

	// movePiece method that handles neighbours checking and the movement
	public static void movePiece(int x, int y) {
		// Make sure at least one cell is highlighted
		if(AbaloneBoard.getLastClickedCell()!=null) {
			// method to check if all active cells can move
			if(canAllActiveCellsMove(x, y)){
				// store all clicked cells
				allClickedCells = AbaloneBoard.getAllClickedCells();
				// active cells piece
				Color activeCellsColor = allClickedCells.get(0).getCurrentPiece();
				
				for (int i = 0; i < allClickedCells.size(); i++) allClickedCells.get(i).setPiece(EMPTY);
				
				// for every clicked cell
				for (int i = 0; i < allClickedCells.size(); i++){
					// convert cell user data to x/y coords
					int[] coords = new int[2];
					coords[0] = Integer.parseInt(allClickedCells.get(i).getUserData().toString().split(",")[0]);
					coords[1] = Integer.parseInt(allClickedCells.get(i).getUserData().toString().split(",")[1]);

					// if direction = x -> set cell in that direction to piece of current cell
					if(direction==0) AbaloneBoard.getCell(coords[0]-1, coords[1]-1).setPiece(activeCellsColor);
					if(direction==1) AbaloneBoard.getCell(coords[0]+1, coords[1]-1).setPiece(activeCellsColor);
					if(direction==2) AbaloneBoard.getCell(coords[0]+2, coords[1]).setPiece(activeCellsColor);
					if(direction==3) AbaloneBoard.getCell(coords[0]+1, coords[1]+1).setPiece(activeCellsColor);
					if(direction==4) AbaloneBoard.getCell(coords[0]-1, coords[1]+1).setPiece(activeCellsColor);
					if(direction==5) AbaloneBoard.getCell(coords[0]-2, coords[1]).setPiece(activeCellsColor);
				}
				// unclick all clicked cells once loop ends
				AbaloneBoard.clearAllClickedCells();
			}
		}
	}

	// this method gets an available direction of one cell, and passes that direction to checkCanAllCellsMoveInDirection(_)
	private static boolean canAllActiveCellsMove(int x, int y){
		
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

	// method to check if neighbours[] contains the cell 
	private static boolean contains(Cell[] neighbours, Cell currCell) {
		return Arrays.stream(neighbours).anyMatch(currCell::equals);
	}
	
	
	
	
	
	
	
}