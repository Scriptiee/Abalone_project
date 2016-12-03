
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class AbaloneBoard extends GridPane {

	// private vars for the class
	// array of cells 
	private static Cell[][] boardCells = new Cell[19][11];
	private final Color EMPTY = Color.TRANSPARENT;
	private final Color PLAYER1 = Color.WHITE;
	private final Color PLAYER2 = Color.BLACK;
	private final Color PIECEOUT = Color.BLUE;
	static ArrayList<Cell> clickedCells = new ArrayList(3);
		
		

	// Board should create master board & add all Cell and Piece to it TODO
	public AbaloneBoard(){
		// position the grid in the center
		setAlignment(Pos.CENTER);
		// set some padding for pretty
		setPadding(new Insets(35,0,0,50));
		// Hgap and Vgap to make it align with the HEX (might be a better way of doing that)
		setHgap(-15);
		setVgap(15);

		// Rendering loop
		for (int i = 0; i < boardCells.length; i++){
			for (int j = 0; j < boardCells[i].length; j++){
				// first and last row
				if(((j==1 || j == 9) && i > 4 && i <= 13 && i%2!=0) ||
					// second and second last row
					((j==2 || j==8) && i >3 && i < 15 && i%2==0) ||
					// third and seventh row
					((j==3 || j==7) && i >=3 && i <=15 && i%2!=0) ||
					// fourth and sixth row
					((j==4 || j==6) && i >=2 && i<=16 && i%2==0) ||
					// center row
					(j==5 && i>=1 && i<=17 && i%2!=0)) {

					// Place player pieces
					// FOR WHITE PIECES
						if((j==1 || j==2) || ((j==3) && i > 6 && i < 12)){ 
							boardCells[i][j] = new Cell(i,j);
							boardCells[i][j].setPiece(PLAYER1);
							add(boardCells[i][j],i,j);
						}
						// FOR BLACK PIECES
						else if ((j==8 || j == 9) || ((j==7) && i > 6 && i < 12)){ 
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
					} else if ((( j==0 || j==10) && i>3 && i< 15 && i%2==0) ||
							((j==1 || j==9) && (i==3 || i==4 || i==14 || i==15)) ||
							((j==2 || j==8) && (i==2 || i==3 || i==15 || i==16)) ||
							((j==3 || j==7) && (i==1 || i==2 || i==16 || i==17)) ||
							((j==4 || j==6) && (i==0 || i==1 || i==17 || i==18)) ||
							((j==5 && (i==0 || i==18)))) {
							boardCells[i][j] = new Cell(i,j);
							boardCells[i][j].setPiece(PIECEOUT);
					} else {
						boardCells[i][j] = null;
					}
				}
			}
		}

	// fills a clickedCells arrays of all currently active cells (safety: if more than 3 are selected -> clear them all
	public static void recordClickedCell(int x, int y){
		if(clickedCells.size() < 3) {
		clickedCells.add(boardCells[x][y]);
		System.out.println("number of clicked cells: " + clickedCells.size());
		System.out.println("cell placed at index: " + clickedCells.indexOf(boardCells[x][y]));
		} else {
			boardCells[x][y].clear();
			clickedCells.forEach(clickedCells->clickedCells.clear());
			clickedCells.clear();
			clickedCells.trimToSize();
		}
	}
	
	public static void untrackClickedCell(int x, int y) {
		if(clickedCells.size() > 0) {
			clickedCells.remove(clickedCells.indexOf(boardCells[x][y]));
			clickedCells.trimToSize();
			System.out.println("New count of clicked cells: " +clickedCells.size());
		}
	}
		
	/*
	*  Display neighbours of all selected cells
	*  Printing for debug purposes only
	*  Probably going to be used in GameLogic for moving pieces
	*/
	public static void displayNeighbours(Cell activeCell){
		// get list of neighbours
		int[][] activeCellNeighbours = activeCell.getNeighbours();
		
		for (int x = 0; x < 6; x++){
			if (x == 0) System.out.println("Cell: "+ activeCell.getUserData()+" has neighbours: \nTop Left: " + activeCellNeighbours[x][0] + "," + activeCellNeighbours[x][1]);
			if (x == 1) System.out.println("Top Right: " + activeCellNeighbours[x][0] + "," + activeCellNeighbours[x][1]);
			if (x == 2) System.out.println("Right: " + activeCellNeighbours[x][0] + "," + activeCellNeighbours[x][1]);
			if (x == 3) System.out.println("Bottom Right: " + activeCellNeighbours[x][0] + "," + activeCellNeighbours[x][1]);
			if (x == 4) System.out.println("Bottom Left: " + activeCellNeighbours[x][0] + "," + activeCellNeighbours[x][1]);
			if (x == 5){ System.out.println("Left: " + activeCellNeighbours[x][0] + "," + activeCellNeighbours[x][1]);System.out.println("============");}
		}
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