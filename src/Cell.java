import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cell extends Pane {

	// vars of the class
	private final Color EMPTY = Color.TRANSPARENT;

	public boolean isClicked = false;

	Circle render = new Circle();
	Piece aPiece = new Piece(EMPTY);
	Circle clickedGraphic = new Circle();

	private Cell[] neighbours = new Cell[6];

	public Cell(int i, int j) {
		this.setUserData(i+","+j); // Set cell column & row to User Data

		// Create cell shape here and add to Pane
		render.setFill(Color.DARKGREY);
		render.setRadius(25);
		getChildren().addAll(render);

		// Colour used when a piece is clicked
		clickedGraphic.setFill(new Color(1,1,0,0.5));
		clickedGraphic.setStroke(Color.YELLOW);
		clickedGraphic.setRadius(24); // 24 fixes jerky resizing of the board when clicking on a piece

		// add a mouse clicked listener that will detect is shift is pressed or not, and add or move pieces
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			// overridden handle method
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().toString() == "PRIMARY"){
					if(event.isShiftDown()){ // if shift key is pressed
						isClicked(i, j);
					} else if(!event.isShiftDown()){ // if shift key is not pressed -> MOVE?
						// Call method responsible for moving the piece
						GameLogic.movePiece(i, j);
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

	// checks if cell contains a piece -> isClicked boolean true/false -> draws/removes highlighted graphic -> update AbaloneBoard
	public void isClicked(int x, int y){
		if(getCurrentPiece()!=EMPTY){
			isClicked = !isClicked;
			if(isClicked){
				// TODO 
				// sanity check to make sure second clicked cell is a neighbour of the first one
				getChildren().add(clickedGraphic);
				AbaloneBoard.recordClickedCell(this);
			} else {
				// if there is a clickedGraphic already in this cell, remove it
				AbaloneBoard.untrackClickedCell(this);
				for (int i = 0; i < getChildren().size(); i++){
					if(getChildren().get(i) == clickedGraphic){ 
						getChildren().remove(i);
					}
				}
			}
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

	// get neighbouring cells and return their position in an array (clockwise beginning from top left)
	public Cell[] getNeighbours(){
		
		String[] cell = getUserData().toString().split(",");
		Integer x = Integer.parseInt(cell[0]); 
		Integer y  = Integer.parseInt(cell[1]);
		
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

	/* GETTERS / SETTERS */
	// get piece currently in this cell
	public Color getCurrentPiece(){
		return aPiece.getPiece();
	}

	// returns isClicked bool
	public boolean getIsClicked(){
		return isClicked;
	}
}

/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * finish mouse (move) click event
 */