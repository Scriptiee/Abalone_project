// base required imports
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
//layout
import javafx.scene.layout.StackPane;


public class Abalone extends Application {
	
	@Override public void init(){
		sp_mainlayout = new StackPane();
		cell = new Cell();
		sp_mainlayout.getChildren().add(cell);
	}
	
	@Override public void start(Stage primaryStage){
		primaryStage.setTitle("JavaFX - Abalone Game");
		primaryStage.setScene(new Scene(sp_mainlayout,700,700));
		primaryStage.show();
				
	}
	
	@Override public void stop(){
		System.out.println("Game Closed");

	}
	
	public static void main(String[] args){
		launch(args);
	}
	
	//private fields
	StackPane sp_mainlayout;
	Cell cell;
}
