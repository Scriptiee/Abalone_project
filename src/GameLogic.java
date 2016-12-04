import java.util.Arrays;

import javafx.scene.paint.Color;

public class GameLogic {
	
		private static Cell lastCell;
		private static Cell newCell;
	
	public static void movePiece(int x, int y) {
		if(AbaloneBoard.getLastClickedCell()!=null) {
			if(!contains(AbaloneBoard.getLastClickedCell().getNeighbours(),AbaloneBoard.getCell(x, y))) {
				System.out.println("This is not a neighbour cell");
				return;			
			}else {
				System.out.println("This is neighbour cell");
				AbaloneBoard.getCell(x, y).setPiece(AbaloneBoard.getLastClickedCell().getCurrentPiece());
				AbaloneBoard.getLastClickedCell().clear();
				AbaloneBoard.getLastClickedCell().setPiece(Color.TRANSPARENT);
				AbaloneBoard.untrackClickedCell(AbaloneBoard.getLastClickedCell());
			
			
			}
		}else {
			System.out.println("No cells clicked yet");
			return;
		}

		
		
		
		
		
		
		
		
		
		
		
		
//		System.out.print("Hilighted cell: " + AbaloneBoard.getLastClickedCell()+ " @ ");
//		System.out.println(AbaloneBoard.getLastClickedCell().getUserData());
//		
//		for(int i =0; i < AbaloneBoard.getLastClickedCell().getNeighbours().length; i++) {
//			System.out.print(AbaloneBoard.getLastClickedCell().getNeighbours()[i] + " @ ");
//			System.out.println(AbaloneBoard.getLastClickedCell().getNeighbours()[i].getUserData());
//		}
//		System.out.print("Clicked cell: " +AbaloneBoard.getCell(x, y) + " @ ");
		
	}
	
	private static boolean contains(Cell[] neighbours, Cell currCell) {
		return Arrays.stream(neighbours).anyMatch(currCell::equals);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}