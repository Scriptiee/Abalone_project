// base required imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
//layout
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Abalone extends Application {
	//private fields
	// Pane to hold the board background
	StackPane hexBg = new StackPane();
	// the hex shape for the background
	Polygon hex = new Polygon();
	// Border pane to separate scores from the the game board
	BorderPane border = new BorderPane();
	// Game score to be held in this one
	FlowPane gamescore = new FlowPane();
	// The actuall board
	AbaloneBoard abaloneBoard = new AbaloneBoard();
	
	// Overridden init method
	@Override public void init(){
		// pints for the polygon class to create the hex shape (better way of doing that?)
		hex.getPoints().addAll(155.0,0.0,
				545.0,0.0,
				720.0,350.0,
				545.0,700.0,
				155.0,700.0,
				-20.0,350.0);
		// add hex to BG
		hexBg.getChildren().add(hex);
		// add board on top of that
		hexBg.getChildren().add(abaloneBoard);
//		hexBg.setStyle("-fx-border-style: solid inside;"); // debug code 
		gamescore.setStyle("-fx-background-color: DAE6F3;"); // debug code for easier visualisation
		gamescore.setPrefSize(300, border.getHeight()); // arbitary size cuz why not
		border.setCenter(hexBg); // set the Pane as a center of our BorderPane
		border.setRight(gamescore); // add the gamescore to the right
		// border.setTop(); <--- could add a menu there to restart game or what not
	}
	
	// Overridden start method
	@Override public void start(Stage primaryStage){
		// hex colours 
		hex.setStroke(Color.BLACK);
		hex.setFill(Color.BLUE);
		// set title, size, set scene and show
		primaryStage.setTitle("JavaFX - Abalone Game");
		// set size (some arbitary number I pulled out of my ass really, but seems to work pretty well
		border.setPrefSize(1100, 700);
		// PRETTY!!
		border.setStyle("-fx-padding: 5;" +
				"-fx-border-style: solid inside;" +
				"-fx-border-width: 2;" +
				"-fx-border-insets: 5;" +
				"-fx-border-radius: 5;" +
				"-fx-border-color: blue;");
		Scene scene = new Scene(border);
		primaryStage.setScene(scene);
		// Disable resizing 
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
