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
	private Polyline playerLine = new Polyline(25.0, 10.0, 275.0, 10.0);
	private Pane scoreCard = new Pane();
	private Label score = new Label();
	private Label timer = new Label();
	
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
		score.setText("0");
		score.setId(id+"-score");
		scoreCard.setPrefSize(150, 100);
		scoreCard.getChildren().add(score);
		timer.setText("120");
		timer.setId(name.replaceAll(" ", ""));
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
	
	public Pane getScoreCard() {
		return scoreCard;
	}
	
	public void setScore(int x) {
		score.setText(String.valueOf(x));
	}
	public Label getTimer() {
		return timer;
	}
	
	public void setTimer(int x) {
		timer.setText(String.valueOf(x));
	}
	
}