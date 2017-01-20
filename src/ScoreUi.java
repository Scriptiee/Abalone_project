import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

public class ScoreUi {
	
	private Circle picture = new Circle(60);
	private StackPane playerPane = new StackPane();
	private Pane player = new Pane();
	private Pane playerColorLine = new Pane();
	private Label playerName = new Label();
	private Polyline playerLine = new Polyline(50.0, 10.0, 250.0, 10.0);
	
	public ScoreUi(Color c, String name, String id) {
		picture.setFill(Color.TRANSPARENT);
		playerName.setText(name);
		playerName.setId(name.replaceAll(" ", ""));
		playerLine.setStroke(c);
		playerLine.setStrokeWidth(5);
		playerColorLine.getChildren().add(playerLine);
		player.setId(id);
		playerPane.getChildren().addAll(picture, player);
		playerPane.setPadding(new Insets(15,15,15,15));
	}
	
	public StackPane getPic() {
		return playerPane;
	}
	
	public Label getName() {
		return playerName;
	}
	
	public Pane getLine() {
		return playerColorLine;
	}
	
	
}