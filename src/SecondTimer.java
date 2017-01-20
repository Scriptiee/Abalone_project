// imports
import java.util.Timer;
import java.util.TimerTask;

public class SecondTimer {

	// declarations
    private Timer timer;
    private int countDown = 120;
    private int secondsLeft;

    // init
    public SecondTimer() {
        timer = new Timer();
    }

    public void start() {
        secondsLeft = countDown;
        // Decrease seconds left every 1 second.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeft--;
                // TODO PRINT secondsLeft to ScoreUi
                if (secondsLeft == 0) {
                    timer.cancel();
                    // TODO Display winner in score ui
                    AbaloneBoard.freezeGame();
                }
            }
        }, 0, 1000);
    }

    public void setCountDown(int seconds) {
        countDown = seconds;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}