// base required imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
//layout
import javafx.scene.layout.StackPane;

public class Abalone extends Application {
	//private fields
	StackPane sp_mainlayout = new StackPane();
	AbaloneBoard abaloneBoard = new AbaloneBoard();
	
	// Overridden init method
	@Override public void init(){
		sp_mainlayout.getChildren().add(abaloneBoard);
	}
	
	// Overridden start method
	@Override public void start(Stage primaryStage){
		// set title, size, set scene and show
		primaryStage.setTitle("JavaFX - Abalone Game");
		primaryStage.setScene(new Scene(sp_mainlayout,890,890)); // resize to 900x900 to add a 9x9 grid of cells easier
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	// Overridden stop method
	@Override public void stop(){
		System.out.println("Game Closed");
	}
	
	// Entry point into our program to launch the JavaFX application
	public static void main(String[] args){
		launch(args);
	}
}
