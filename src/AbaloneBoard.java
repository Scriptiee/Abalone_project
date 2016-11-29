import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AbaloneBoard extends GridPane {
	
	// private vars for the class
	Polygon hex = new Polygon();
	Cell render = new Cell();
	
	private Cell[][] test = new Cell[9][9];
	
	// Board should create master board & add all Cell and Piece to it TODO
	public AbaloneBoard(){
		hex.setStroke(Color.BLACK);
		hex.setFill(Color.TRANSPARENT);
		
		getChildren().add(hex);
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				test[i][j] = new Cell();
				test[i][j].setTranslateX(100*j);
				//test[i][j].setTranslateX(getTranslateX()+50);
				getChildren().add(i,test[i][j]);
			}
		}
		
	}
	
	// Overridden resize method
	// DO WE WANT TO RESIZE? the edges should be all the same length as
	// well as all angles beeing 120 degrees as that is the property of 
	// hexagon. Maybe look into hardcoding the size of the board and disabling 
	// resizing altogether?
	@Override
	public void resize(double width, double height){
		super.resize(width, height);
		double singleCellSizeWidth = width/9;	// Size of one cell width
		double singleCellSizeHeight = height/9; // Size of one cell height
		
		hex.getPoints().clear(); // Clear previous points
		
		// Calculate all points for the Hexagon
		double topLeft = width-(singleCellSizeWidth*7);
		double topRight = width-(singleCellSizeWidth*2);
		double middleRightX = width;
		double middleRightY = height-(singleCellSizeHeight*4.5);
		double bottomRightX = width-(singleCellSizeWidth*2);
		double bottomRightY = height;
		double bottomLeftX = width-(singleCellSizeWidth*7);
		double bottomLeftY = height;
		double middleLeftY = height-(singleCellSizeHeight*4.5);
		
		// Add resized points to hexagon
		hex.getPoints().addAll(new Double[]{
				topLeft,0.0, // top left
				topRight,0.0, // top right
				middleRightX,middleRightY,// middle right
				bottomRightX,bottomRightY,// bottom right
				bottomLeftX,bottomLeftY,// bottom left
				0.0,middleLeftY  // middle left
		});
	}
}

/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * reset game method
*/