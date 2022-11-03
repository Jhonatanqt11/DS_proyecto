import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Observable {
  private LocalDateTime time;
  private static Clock instance = null;
  private Timer timer;

  public static Clock getInstance(){
    if (instance == null)
      instance = new Clock();
    return instance;
  }


  private Clock()
  {
    time = LocalDateTime.now().minusSeconds(2);
    //  Consequently we have to set the new interval duration to 2 seconds the initial date-time to 2 seconds before the first signal receive
  }


  private void tick(){
    time = LocalDateTime.now();
    setChanged();
    notifyObservers(this);
  }

  public void startTimer(){
    timer = new Timer(true);
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        tick();
      }
    };
    timer.scheduleAtFixedRate(timerTask,1500,2000);
  }

  public LocalDateTime getTime()
  {
    return time;
  }

  public synchronized void stopTimer()
  {
    timer.cancel();
    timer.purge();
  }
}
