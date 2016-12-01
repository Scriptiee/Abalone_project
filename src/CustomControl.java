//class handling the controls for the game
import javafx.scene.control.Control;

//events
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CustomControl extends Control {

	//Constructor
	public CustomControl(){
		setSkin(new CustomControlSkin(this));
		
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
}

/* -----STILL NEEDS----- 
 * (take a comment and work on it)
 * finish space key reset method
 */