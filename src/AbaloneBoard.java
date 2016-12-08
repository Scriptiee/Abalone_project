
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

	// Board creates master board & adds all Cell and Piece to it
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
					boardCells[i][j].setCanBeClicked(false);
					add(boardCells[i][j],i,j);
				} else {
					boardCells[i][j] = null;
				}
			}
		}
	}

	// fills a clickedCells ArrayList to keep track of currently clicked cells -> clear if more than 3 clicked
	public static boolean recordClickedCell(Cell cell){
		// if cell clicked is first cell clicked -> always return true
			clickedCells.add(boardCells[cell.getCellPos()[0]][cell.getCellPos()[1]]);
			return true;
	}
	
	// is the clicked cell a neighbour
	public static boolean isClickedCellANeighbour(Cell cell){
		if(clickedCells.size() == 0) return true;
		// if this is the third clicked cell, make sure it is in line
		if(thirdPieceInLine(cell)){
			// ensure shift-clicked cell is a neighbour of at least one previously clicked cell
			for (int i = 0; i < clickedCells.size(); i++){
				Cell[] cellNeighbours = clickedCells.get(i).getAllNeighbouringCells();
				for (int j = 0; j < cellNeighbours.length; j++){
					if (cellNeighbours[j] == cell){
						// select cell
						if(clickedCells.size() < 3) {
							return true;
						} else {
							// this is to ignore the 4th click and 
							boardCells[cell.getCellPos()[0]][cell.getCellPos()[1]].clear();
							// clear all cells -- moved to method to use in other classes
							clearAllClickedCells();
							// Trim it to Size 0
							clickedCells.trimToSize();
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	// checks if third piece is in line with first and second
	public static boolean thirdPieceInLine(Cell thirdCell){
		if(clickedCells.size()==1) return true; // safety
		Cell[] cellNeighboursOfFirstPiece = clickedCells.get(0).getAllNeighbouringCells(); // Store all neighbours of first cell
		Cell[] cellNeighboursOfSecondPiece = clickedCells.get(1).getAllNeighbouringCells(); // Store all neighbours of second cell

		// add to end of second piece
		for (int i = 0; i < cellNeighboursOfFirstPiece.length; i++){			// for all i length of neighbours of first piece
			if(cellNeighboursOfFirstPiece[i] == clickedCells.get(1)){			// if neighboursOfFirst = second Clicked Cell [i = DIRECTION]
				for (int j = 0; j < cellNeighboursOfSecondPiece.length; j++){	// for all j length of neighbours of second piece
					if(cellNeighboursOfSecondPiece[j] == thirdCell){			// if neighboursOfSecond = third clicked cell [j = DIRECTION]
						if (i == j){											// if i == j -> same direction -> so true
							return true;
						}
					}
				}
			}
		}
		// add to end of first piece
		for (int i = 0; i < cellNeighboursOfFirstPiece.length; i++){			// for all i length of neighbours of first piece
			if(cellNeighboursOfSecondPiece[i] == clickedCells.get(0)){			// if neighboursOfFirst = second Clicked Cell [i = DIRECTION]
				for (int j = 0; j < cellNeighboursOfSecondPiece.length; j++){	// for all j length of neighbours of second piece
					if(cellNeighboursOfFirstPiece[j] == thirdCell){			// if neighboursOfSecond = third clicked cell [j = DIRECTION]
						if (i == j){											// if i == j -> same direction -> so true
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	// Check if cell clicked is the middle cell
	public static boolean isMiddleCell(Cell cell){
		if(clickedCells.size() != 3) return false; // must be 3 cells clicked
		Cell[] cellNeighboursOfFirstPiece = clickedCells.get(0).getAllNeighbouringCells(); // Store all neighbours of first cell
		Cell[] cellNeighboursOfSecondPiece = clickedCells.get(1).getAllNeighbouringCells(); // Store all neighbours of second cell
		Cell[] cellNeighboursOfThirdPiece = clickedCells.get(2).getAllNeighbouringCells(); // Store all neighbours of third cell
		
		// Checking if given cell is a neighbour of two other clicked cells (must be middle piece)
		for (int i = 0; i < cellNeighboursOfFirstPiece.length; i++){
			for (int j = 0; j < cellNeighboursOfSecondPiece.length; j++){
				if(cell == cellNeighboursOfFirstPiece[i] && cell == cellNeighboursOfSecondPiece[j]) return true;
			}
		}
		for (int i = 0; i < cellNeighboursOfFirstPiece.length; i++){
			for (int j = 0; j < cellNeighboursOfThirdPiece.length; j++){
				if(cell == cellNeighboursOfFirstPiece[i] && cell == cellNeighboursOfThirdPiece[j]) return true;
			}
		}
		for (int i = 0; i < cellNeighboursOfSecondPiece.length; i++){
			for (int j = 0; j < cellNeighboursOfThirdPiece.length; j++){
				if(cell == cellNeighboursOfSecondPiece[i] && cell == cellNeighboursOfThirdPiece[j]) return true;
			}
		}
		return false;
	}

	// ensure active pieces are same as new clicked piece
	public static boolean isAllActivePiecesSameColor(Cell cell){
		for (int x = 0; x < clickedCells.size(); x++){
			if (clickedCells.get(x).getCurrentPiece() != cell.getCurrentPiece()){
				return false;
			}
		}
		return true;
	}

	// removes a cell from ArrayList if clicked again
	public static void untrackClickedCell(Cell cell) {
		// sanity check though probably not necessary as isClicked is kind of handling this
		if(clickedCells.size() > 0) {
			// remove the cell from the ArrayList
			clickedCells.remove(clickedCells.indexOf(boardCells[cell.getCellPos()[0]][cell.getCellPos()[1]]));
			// Trim the size of the array
			clickedCells.trimToSize();
		}
	}

	// clear all clicked cells
	public static void clearAllClickedCells(){
		// fancy clear all cells 
		clickedCells.forEach(clickedCell->clickedCell.clear());
		// Clear the ArrayList
		clickedCells.clear();
	}

	// return clickedCells array 
	public static ArrayList<Cell> getAllClickedCells(){
		return clickedCells;
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
}

/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * reset game method
 */ 