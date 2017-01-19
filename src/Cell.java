import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cell extends Pane {

	// vars of the class
	private int[] cellPos = new int[2];

	private final Color EMPTY = Color.web("#2139A6");
	public boolean isClicked = false;
	private boolean canBeClicked = true;
	private Cell[] neighbours = new Cell[6];

	private Circle render = new Circle();
	private Piece aPiece = new Piece(EMPTY);
	private Circle clickedGraphic = new Circle();

	public Cell(int i, int j) {
		// Set position of cell in array for reference
		cellPos[0] = i;
		cellPos[1] = j;

		// Create cell shape here and add to Pane
		render.setFill(Color.web("#2139A6"));
		render.setRadius(25);
		render.setStroke(new Color(0,0,0,0.56));
		getChildren().addAll(render);

		// Colour used when a piece is clicked
		clickedGraphic.setFill(new Color(0,0,0,0.2));
		clickedGraphic.setStroke(Color.RED);
		clickedGraphic.setRadius(25);  

		// add a mouse clicked listener that will detect if shift is pressed or not, and add or move pieces
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			// overridden handle method
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().toString() == "PRIMARY"){
					if(canBeClicked){
						if(event.isShiftDown()){ 							// IF shift key is pressed
							isClicked(i, j);								// -> click cell
						} else if(!event.isShiftDown()){ 					// ELSE IF shift key is not pressed
							if(AbaloneBoard.getAllClickedCells().size() > 0)// -> if at least one piece clicked
								MovementAndPushing.movePiece(i, j);			// --> move piece
						}
					}
				}
			}
		});
	}

	// set a new piece in this cell
	public void setPiece(Color player){
		// if there is a piece already in this cell, remove it
		for (int i = 0; i < getChildren().size(); i++){
			if(getChildren().get(i) == aPiece){ 
				getChildren().remove(i);
			}
		}
		aPiece = new Piece(player); // create new piece with parameters
		getChildren().add(aPiece); // add new piece to the board
	}

	// handles shift-clicking of cells
	public void isClicked(int x, int y){
		// IF CELL IS ALREADY CLICKED
		if(isClicked){
			isClicked = !isClicked;
			if(AbaloneBoard.isMiddleCell(this)){					// is cell the middle of 3
				AbaloneBoard.clearAllClickedCells();				// true -> remove all cells
			} else {												// false -> remove clicked cell
				AbaloneBoard.untrackClickedCell(this);
				clear();
			}
		} // IF CELL IS NOT ALREADY CLICKED
		else if (this.getCurrentPiece() != EMPTY){
			if(AbaloneBoard.isAllActivePiecesSameColor(this)){	// check if active piece type = new active piece type
				if (AbaloneBoard.isClickedCellANeighbour(this)){		// check if cell a neighbour of previous click
					if (AbaloneBoard.recordClickedCell(this)){			// record click in AbaloneBoard
						if(!isClicked){
							getChildren().add(clickedGraphic);
							isClicked = !isClicked;
						}
					}
				}
			}
		} else {
			// if a different colour piece is shift-clicked -> wipe
			AbaloneBoard.clearAllClickedCells();
		}
	}

	// set cell unclicked & remove highlight
	public void clear(){
		isClicked = false;
		for (int i = 0; i < getChildren().size(); i++){
			if(getChildren().get(i) == clickedGraphic){ 
				getChildren().remove(i);
			}
		}
	}

	/* GETTERS / SETTERS */
	// Get cells position in array
	public int[] getCellPos(){
		return cellPos;
	}

	// enable or disable whether the cell is clickable or not
	public void setCanBeClicked(boolean TorF){
		canBeClicked = TorF;
	}

	// get neighbouring cells and return their position in an array (clockwise beginning from top left)
	public Cell[] getAllNeighbouringCells(){
		int x = cellPos[0]; 
		int y  = cellPos[1];

		for(int i=0; i < neighbours.length; i++) {
			if(i==0) neighbours[i] = AbaloneBoard.getCell(x-1, y-1);
			if(i==1) neighbours[i] = AbaloneBoard.getCell(x+1, y-1);
			if(i==2) neighbours[i] = AbaloneBoard.getCell(x+2, y);
			if(i==3) neighbours[i] = AbaloneBoard.getCell(x+1, y+1);
			if(i==4) neighbours[i] = AbaloneBoard.getCell(x-1, y+1);
			if(i==5) neighbours[i] = AbaloneBoard.getCell(x-2, y);
		}
		return neighbours;
	}

	// return color of neighbour piece in a given direction
	public Color getNeighbourPiece(int direction){
		int x = cellPos[0]; 
		int y  = cellPos[1];

		if(direction==0) return AbaloneBoard.getCell(x-1, y-1).getCurrentPiece();
		if(direction==1) return AbaloneBoard.getCell(x+1, y-1).getCurrentPiece();
		if(direction==2) return AbaloneBoard.getCell(x+2, y).getCurrentPiece();
		if(direction==3) return AbaloneBoard.getCell(x+1, y+1).getCurrentPiece();
		if(direction==4) return AbaloneBoard.getCell(x-1, y+1).getCurrentPiece();
		if(direction==5) return AbaloneBoard.getCell(x-2, y).getCurrentPiece();

		return EMPTY;
	}

	// get piece currently in this cell
	public Color getCurrentPiece(){
		return aPiece.getPiece();
	}

	// returns isClicked bool
	public boolean getIsClicked(){
		return isClicked;
	}
}