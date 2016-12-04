import java.util.Arrays;

import javafx.scene.paint.Color;

public class GameLogic {
	
	
	// movePice method that handles neighbours checking and the movement
	public static void movePiece(int x, int y) {
		// Make sure at least one cell is hilighted
		if(AbaloneBoard.getLastClickedCell()!=null) {
			// check if the cell clicked without the SHIFT belongs to the neighbours of the hilighted cell
			if(!contains(AbaloneBoard.getLastClickedCell().getNeighbours(),AbaloneBoard.getCell(x, y))) {
				System.out.println("This is not a neighbour cell");
				return;			
			}else {
				System.out.println("This is neighbour cell");
				// set the new cell to be the same color as the LastClicked cell
				AbaloneBoard.getCell(x, y).setPiece(AbaloneBoard.getLastClickedCell().getCurrentPiece());
				// clear that LastClicked cell
				AbaloneBoard.getLastClickedCell().clear();
				// set it to EMPTY player - better way of doing it?
				AbaloneBoard.getLastClickedCell().setPiece(Color.TRANSPARENT);
				// untrack the Cell 
				AbaloneBoard.untrackClickedCell(AbaloneBoard.getLastClickedCell());
				}
		}else {
			System.out.println("No cells clicked yet");
			return;
		}

	
	}
	
	// method to check if neighbours[] contains the cell 
	private static boolean contains(Cell[] neighbours, Cell currCell) {
		return Arrays.stream(neighbours).anyMatch(currCell::equals);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}