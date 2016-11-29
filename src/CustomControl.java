//class handling the controls for the game
import javafx.scene.control.Control;

//events
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CustomControl extends Control {

	//Constructor
	public CustomControl(){
		setSkin(new CustomControlSkin(this));
		System.out.println("here");

		// add a mouse clicked listener that will detect is shift is pressed or not, and add or move pieces
		setOnMouseClicked(new EventHandler<MouseEvent>() {
			// overridden handle method
			@Override
			public void handle(MouseEvent event) {
				if(event.isShiftDown()){ // if shift key is pressed
					System.out.println("Shift-mouse clicked at: X"+event.getX()+" Y"+event.getY()); // Test code - should call movePieces method in AbaloneBoard() TODO
				} else if(!event.isShiftDown()){ // if shift key is not pressed
					System.out.println("Mouse clicked at: X"+event.getX()+" Y"+event.getY()); // Test code - should call addSelectedPieces method in AbaloneBoard() TODO
				}
			}
		});
		
		// add a key listener for SPACE that will reset the game
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			// overridden handle method
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.SPACE){
					System.out.println("Call abaloneBoard.resetGame()"); // TODO
				}
			}
		});
	}

	// override the resize method
	@Override
	public void resize(double width, double height) {
		// update the size of the rectangle
		super.resize(width, height);
	}
}

/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * finish shift-mouse clicked method
 * finish mouse clicked method
 * finish space key reset method
 */