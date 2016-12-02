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

	private int[][] neighbours = new int[6][2];

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
						isClicked();
					} else if(!event.isShiftDown()){ // if shift key is not pressed -> MOVE?
						//TODO
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
	public void isClicked(){
		if(getCurrentPiece()!=EMPTY){
			isClicked = !isClicked;
			if(isClicked){
				getChildren().add(clickedGraphic);
			} else {
				// if there is a clickedGraphic already in this cell, remove it
				for (int i = 0; i < getChildren().size(); i++){
					if(getChildren().get(i) == clickedGraphic){ 
						getChildren().remove(i);
					}
				}
			}
			AbaloneBoard.listAllClickedCells();
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
	public int[][] getNeighbours(){
		// convert userData from string to two int values
		String[] cell = getUserData().toString().split(",");
		int[] cellNum = new int[2];
		cellNum[0] = Integer.parseInt(cell[0]);
		cellNum[1] = Integer.parseInt(cell[1]);

		// top left
		neighbours[0][0] = (cellNum[0]-1); neighbours[0][1] = (cellNum[1]-1);
		// top right
		neighbours[1][0] = (cellNum[0]+1);neighbours[1][1] = (cellNum[1]-1);
		// right
		neighbours[2][0] = (cellNum[0]+2);neighbours[2][1] = (cellNum[1]);
		// bottom right
		neighbours[3][0] = (cellNum[0]+1);neighbours[3][1] = (cellNum[1]+1);
		// bottom left
		neighbours[4][0] = (cellNum[0]-1);neighbours[4][1] = (cellNum[1]+1);
		// left
		neighbours[5][0] = (cellNum[0]-2);neighbours[5][1] = (cellNum[1]);

		// providing neighbours[position][column/row]
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