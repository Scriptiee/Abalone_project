import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class AbaloneBoard extends Pane {
	
	// private vars for the class
	Polygon hex = new Polygon();
	
	// Board should create master board & add all Cell and Piece to it TODO
	public AbaloneBoard(){
		hex.setStroke(Color.BLACK);
		hex.setFill(Color.BLUE);

		getChildren().addAll(hex);
	}
	
	// Overridden resize method
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
		double middleRightY = height-(singleCellSizeHeight*5);
		double bottomRightX = width-(singleCellSizeWidth*2);
		double bottomRightY = height;
		double bottomLeftX = width-(singleCellSizeWidth*7);
		double bottomLeftY = height;
		double middleLeftY = height-(singleCellSizeHeight*5);
		
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