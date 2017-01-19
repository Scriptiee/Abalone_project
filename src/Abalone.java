// base required imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
//layout
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Abalone extends Application {
	//private fields
	// Pane to hold the board background
	StackPane hexBg = new StackPane();
	// the hex shape for the background
	Polygon hex = new Polygon();
	// Border pane to separate scores from the the game board
	BorderPane border = new BorderPane();
	// Game score to be held in this one could replace with VBox tbh, no need for flow pane unless we will introduce 4 player variant
	FlowPane gamescore = new FlowPane();
	StackPane p1Score = new StackPane();
	StackPane p2Score = new StackPane();
	// The actual board
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
		p1Score.getStyleClass().add("score");
		p2Score.getStyleClass().add("score");
		p1Score.setPrefSize(300, 300);
		p2Score.setPrefSize(300, 300);
		gamescore.setPadding(new Insets(15, 0, 15, 0)); 
		gamescore.setPrefSize(300, border.getHeight()); // arbitrary size cuz why not
		gamescore.setVgap(60); 
		gamescore.setHgap(15); 
		gamescore.getChildren().add(p1Score);
		gamescore.getChildren().add(p2Score);
		Circle p1Pic = new Circle(60);
		StackPane p1Pane = new StackPane();
		Pane p1 = new Pane();
		p1.setId("p1");
		p1Pane.getChildren().addAll(p1Pic, p1);
		p1Score.getChildren().add(new AnchorPane(p1Pane));

		Circle p2Pic = new Circle(60);
		StackPane p2Pane = new StackPane();
		Pane p2 = new Pane();
		p2.setId("p2");
		p2Pane.getChildren().addAll(p2Pic, p2);
		p2Score.getChildren().add(new AnchorPane(p2Pane));
		
		
		border.setCenter(hexBg); // set the Pane as a center of our BorderPane
		border.setRight(gamescore); // add the gamescore to the right
		// border.setTop(); <--- could add a menu there to restart game or what not
	}
	
	// Overridden start method
	@Override public void start(Stage primaryStage){
		border.setId("border");
		// hex colours 
		hex.setFill(Color.web("#2642BF"));
		// set title, size, set scene and show
		primaryStage.setTitle("JavaFX - Abalone Game");
		// set size (some arbitrary number I pulled out of my ass really, but seems to work pretty well
		border.setPrefSize(1100, 700);
		// PRETTY!!
		Scene scene = new Scene(border);
		scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
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
