
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

public class ScoreUi {
	
	private Circle picture = new Circle(60);
	private static StackPane playerPane = new StackPane();
	private Pane player = new Pane();
	private Pane playerColorLine = new Pane();
	private Label playerName = new Label();
	private Polyline playerLine = new Polyline(50.0, 10.0, 250.0, 10.0);
	private Label timer = new Label();
	
	public ScoreUi(Color c, String name, String id) {
		picture.setFill(Color.TRANSPARENT);
		playerName.setText(name);
		playerName.setId(name.replaceAll(" ", ""));
		timer.setText("0");
		playerLine.setStroke(c);
		playerLine.setStrokeWidth(5);
		playerColorLine.getChildren().add(playerLine);
		player.setId(id);
		playerPane.getChildren().addAll(picture, player, timer);
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
	
	public void updateTime(String time){
		for (int i = 0; i < playerPane.getChildren().size(); i++){
			if (playerPane.getChildren().get(i) == timer) playerPane.getChildren().remove(i);
		}
		timer.setText(time);
		playerPane.getChildren().add(timer);
	}
}