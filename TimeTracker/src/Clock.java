import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;


/*IMPORTANTE:
 Interesa tener una unica instancia de reloj que "cuente" el paso del tiempo, se ha implementado el patron Singleton para que la instacia solo pueda ser creada des de la propia clase*/
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
  }


  private void tick(){
    time = LocalDateTime.now();
    setChanged();
    notifyObservers(this);
  }

  /*IMPORTANTE:
   Se inicializa un Timer de una tarea */
  public void startTimer(){
    timer = new Timer();//  Consequently we have to set the new interval duration to 2 seconds the initial date-time to 2 seconds before the first signal receive
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
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

  public synchronized void stopTimer()
  {
    timer.cancel();
    timer.purge();
  }
}
