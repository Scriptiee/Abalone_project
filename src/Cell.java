import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cell extends Pane {

	// vars of the class
	private int[] cellPos = new int[2];

	private final Color EMPTY = Color.TRANSPARENT;
	private final Color PIECEOUT = Color.BLUE;
	public boolean isClicked = false;
	private boolean canBeClicked = true;
	private Cell[] neighbours = new Cell[6];

	private Circle render = new Circle();
	private Piece aPiece = new Piece(EMPTY);
	private Circle clickedGraphic = new Circle();
	private Circle availableMoveGraphic = new Circle();

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


		// Colour used when highlighting available moves
		availableMoveGraphic.setFill(new Color(1,0,0,0.5));
		availableMoveGraphic.setStroke(Color.RED);
		availableMoveGraphic.setRadius(24); 

		// add a mouse clicked listener that will detect if shift is pressed or not, and add or move pieces
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			// overridden handle method
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().toString() == "PRIMARY"){
					if(canBeClicked){
						if(event.isShiftDown()){ 							// IF shift key is pressed
							if(getCurrentPiece() == GameLogic.getCurrentPlayer()){ // IF is current player
								isClicked(i, j);								// -> click cell
							}
						} else if(!event.isShiftDown()){ 					// ELSE IF shift key is not pressed

							if(AbaloneBoard.getAllClickedCells().size() > 0){// -> if at least one piece clicked
								// remove all available move highlight graphic
								for(int i = 0; i < AbaloneBoard.getAllClickedCells().size(); i++){
									Cell[] neighbours = AbaloneBoard.getAllClickedCells().get(i).getAllNeighbouringCells();
									for(int j = 0; j < 6; j++){
										if (neighbours[j].hasAvailableMoveGraphic()) neighbours[j].removeAvailableMoveGraphic();
									}
								}
								MovementAndPushing.movePiece(i, j);			// --> move piece
							}
						}
					}

				}
				if (event.getButton().toString() == "SECONDARY"){
					
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
				for (int i = 0; i < AbaloneBoard.clickedCells.size(); i++){
					Cell[] thisCellNeighbours = AbaloneBoard.clickedCells.get(i).getAllNeighbouringCells();
					for (int j = 0; j < thisCellNeighbours.length; j++){
						thisCellNeighbours[j].removeAvailableMoveGraphic();
					}
				}
				AbaloneBoard.untrackClickedCell(this);
				clear();
			}
		} // IF CELL IS NOT ALREADY CLICKED
		else if (this.getCurrentPiece() != EMPTY){
			if(AbaloneBoard.isAllActivePiecesSameColor(this)){			// check if active piece type = new active piece type
				if (AbaloneBoard.isClickedCellANeighbour(this)){		// check if cell a neighbour of previous click
					if (AbaloneBoard.recordClickedCell(this)){			// record click in AbaloneBoard
						if(!isClicked){
							getChildren().add(clickedGraphic);
							isClicked = !isClicked;
							highlightEmptyNeighbours();
						}
					}
				}
			}
		} else {
			// if a different colour piece is shift-clicked -> wipe
			AbaloneBoard.clearAllClickedCells();
		}
	}

	// highlight places the player can move to
	public void highlightEmptyNeighbours() {
		ArrayList<Cell> allClickedCells = AbaloneBoard.clickedCells;
		Cell[] neighbouringCells = getAllNeighbouringCells();
		int direction = 7;

		// if more than 1 piece selected set the direction they are in
		if(allClickedCells.size() > 1) direction = MovementAndPushing.getDirectionOfMovement(cellPos[0], cellPos[1]);

		// IF ONLY ONE PIECE IS CLICKED, APPLY HIGHLIGHT
		if (direction == 7){
			for(int i = 0; i < 6; i++){
				if(neighbouringCells[i].getCurrentPiece() == EMPTY 
						|| (neighbouringCells[i].getCurrentPiece() != aPiece.getPiece()) 
						&& neighbouringCells[i].getCurrentPiece() != PIECEOUT){
					neighbouringCells[i].setAvailableMoveGraphic();
				}
			}
		} else if(allClickedCells.size() == 2){ // IF TWO PIECES CLICKED...
			neighbouringCells = allClickedCells.get(1).getAllNeighbouringCells();
			for (int i = 0; i < neighbouringCells.length; i++){
				if (!neighbouringCells[i].hasAvailableMoveGraphic()){
					if(neighbouringCells[i].getCurrentPiece() == EMPTY 
							|| (neighbouringCells[i].getCurrentPiece() != aPiece.getPiece()) 
							&& neighbouringCells[i].getCurrentPiece() != PIECEOUT){
						neighbouringCells[i].setAvailableMoveGraphic();
					}
				}
			}
		} else if(allClickedCells.size() == 3){ // IF THREE PIECES CLICKED...
			neighbouringCells = allClickedCells.get(2).getAllNeighbouringCells();
			for (int i = 0; i < neighbouringCells.length; i++){
				if (!neighbouringCells[i].hasAvailableMoveGraphic()){
					if(neighbouringCells[i].getCurrentPiece() == EMPTY 
							|| (neighbouringCells[i].getCurrentPiece() != aPiece.getPiece()) 
							&& neighbouringCells[i].getCurrentPiece() != PIECEOUT){
						neighbouringCells[i].setAvailableMoveGraphic();
					}
				}
			}
		}  
	}

	public void setAvailableMoveGraphic(){
		getChildren().add(availableMoveGraphic);
	}

	public void removeAvailableMoveGraphic(){
		getChildren().remove(availableMoveGraphic);
	}

	public boolean hasAvailableMoveGraphic(){
		for (int i = 0; i < getChildren().size(); i++){
			if(getChildren().get(i) == availableMoveGraphic){ 
				return true;
			}
		}
		return false;
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