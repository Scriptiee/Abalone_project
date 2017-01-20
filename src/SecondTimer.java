// imports
import java.util.Timer;
import java.util.TimerTask;

public class SecondTimer {

	// declarations
    private static Timer timer;
    private static int countDown = 120;
    private static int secondsLeft;

    // init
    public SecondTimer() {
        timer = new Timer();
    }

    public static void start() {
        secondsLeft = countDown;
        // Decrease seconds left every 1 second.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeft--;
                if (secondsLeft == 0) {
                    timer.cancel();
                    AbaloneBoard.freezeGame();
                }
            }
        }, 0, 1000);
    }

    public static void reset() {
    	secondsLeft = 120;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}