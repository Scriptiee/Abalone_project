
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class AbaloneBoard extends GridPane {

	// private vars for the class
	// array of cells 
	private static Cell[][] boardCells = new Cell[21][11];
	private final Color EMPTY = Color.TRANSPARENT;
	private final Color PLAYER1 = Color.WHITE;
	private final Color PLAYER2 = Color.BLACK;
	private final Color PIECEOUT = Color.BLUE;
	static ArrayList<Cell> clickedCells = new ArrayList<Cell>(3);
		
		

	// Board should create master board & add all Cell and Piece to it TODO
	public AbaloneBoard(){
		// position the grid in the center
		setAlignment(Pos.CENTER);
		// set some padding for pretty
		setPadding(new Insets(35,0,0,70));
		// Hgap and Vgap to make it align with the HEX (might be a better way of doing that)
		setHgap(-15);
		setVgap(15);

		// Rendering loop
		for (int i = 0; i < boardCells.length; i++){
			for (int j = 0; j < boardCells[i].length; j++){
				// first and last row
				if(((j==1 || j == 9) && i > 5 && i < 15 && i%2==0) ||
					// second and second last row
					((j==2 || j==8) && i >4 && i < 16 && i%2!=0) ||
					// third and seventh row
					((j==3 || j==7) && i >3 && i <17 && i%2==0) ||
					// fourth and sixth row
					((j==4 || j==6) && i >2 && i<18 && i%2!=0) ||
					// center row
					(j==5 && i>1 && i<19 && i%2==0)) {

					// Place player pieces
					// FOR WHITE PIECES
						if((j==1 || j==2) || ((j==3) && i > 7 && i < 13)){ 
							boardCells[i][j] = new Cell(i,j);
							boardCells[i][j].setPiece(PLAYER1);
							add(boardCells[i][j],i,j);
						}
						// FOR BLACK PIECES
						else if ((j==8 || j == 9) || ((j==7) && i > 7 && i < 13)){ 
							boardCells[i][j] = new Cell(i,j);
							boardCells[i][j].setPiece(PLAYER2);
							add(boardCells[i][j],i,j);
						}
						// FOR EMPTY PIECES
						else {	
							boardCells[i][j] = new Cell(i,j);
							boardCells[i][j].setPiece(EMPTY);
							add(boardCells[i][j],i,j);
						}
					// edge/exit cells (better way of tracking when something goes of the board?)
					} else if ((( j==0 || j==10) && i>4 && i< 16 && i%2!=0) ||
							((j==1 || j==9) && (i==4 || i==16)) ||
							((j==2 || j==8) && (i==3 || i==17)) ||
							((j==3 || j==7) && (i==2 || i==18)) ||
							((j==4 || j==6) && (i==1 || i==19)) ||
							((j==5 && (i==0 || i==20)))) {
							boardCells[i][j] = new Cell(i,j);
							boardCells[i][j].setPiece(PIECEOUT);
					} else {
						boardCells[i][j] = null;
					}
				}
			}
		}

	// fills a clickedCells ArrayList to keep track of currently clicked cells -> clear if more than 3 clicked
	public static void recordClickedCell(Cell cell){
		if(clickedCells.size() < 3) {
		clickedCells.add(boardCells[getCoords(cell)[0]][getCoords(cell)[1]]);
		// DEBUG PRINT
		System.out.println("number of clicked cells: " + clickedCells.size());
		System.out.println("cell placed at index: " + clickedCells.indexOf(boardCells[getCoords(cell)[0]][getCoords(cell)[1]]));
		// END OF DEBUG PRINT
		} else {
			// this is to ignore the 4th click and 
			boardCells[getCoords(cell)[0]][getCoords(cell)[1]].clear();
			// fancy clear all cells 
			clickedCells.forEach(clickedCell->clickedCell.clear());
			// Clear the ArrayList
			clickedCells.clear();
			// Trim it to Size 0
			clickedCells.trimToSize();
		}
	}
	
	// removes a cell from ArrayList if clicked again
	public static void untrackClickedCell(Cell cell) {
		// sanity check though probably not necessary as isClicked is kind of handling this
		if(clickedCells.size() > 0) {
			// remove the cell from the ArrayList
			clickedCells.remove(clickedCells.indexOf(boardCells[getCoords(cell)[0]][getCoords(cell)[1]]));
			// Trim the size of the array
			clickedCells.trimToSize();
			// DEBUG PRINT
			System.out.println("New count of clicked cells: " +clickedCells.size());
			// END OF DEBUG PRINT
		}
	}
	
	// return last element in the ArrayList
	public static Cell getLastClickedCell() {
		if(!clickedCells.isEmpty()) { 
			return clickedCells.get(clickedCells.size()-1);
		}
		return null;		
	}
		
	// return reference to a cell at x,y location
	public static Cell getCell(int x, int y) {
		return boardCells[x][y];			
	}
	
	// helper method to get cell coordinations from getUserData(), might be usefull at later stage
	public static int[] getCoords(Cell cell) {
		int[] coords = new int[2];
		coords[0] = Integer.parseInt(cell.getUserData().toString().split(",")[0]);
		coords[1] = Integer.parseInt(cell.getUserData().toString().split(",")[1]);
		
		return coords;
		
	}
		
	// get piece in a given cell
	public Color getCurrentPieceInCell(int i, int j){
		return boardCells[i][j].getCurrentPiece();
	}
}

/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * reset game method
 */ 