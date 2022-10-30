import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Clock extends Observable {
  private LocalDateTime time;
  private static Clock instance;
  private Timer timer;

  public Clock getInstance(){
    if (instance == null)
      instance = new Clock();
    return instance;
  }

  /*
  private Clock()
  {
    time = LocalDateTime;
    lDateTime.now().minusSeconds(2); //  Consequently we have to set the new interval duration to 2 seconds the initial date-time to 2 seconds before the first signal received
  }
   */

  private void tick(){
    time = LocalDateTime.now();
    setChanged();
    notifyObservers(this);
  }
  public void startTimer(){
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
        String timeString = time.format(formatter);
        System.out.println(timeString);
        tick();
      }
    };
    timer.scheduleAtFixedRate(timerTask,0,2000);
  }

  public LocalDateTime getTime()
  {
    return time;
  }

  public void stopTimer()
  {
    timer.cancel();
    timer.purge();
  }
}
