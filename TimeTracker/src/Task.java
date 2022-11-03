import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class Task extends Activity {
  private List<Interval> intervals = new ArrayList<Interval>();
  private DateTimeFormatter initD;
  private DateTimeFormatter finalD;

  public Task(String n){
    super(n);
    intervals = new ArrayList<Interval>();
  }


  /*IMPORTANTE:
  El TotalTime de una actividad es la suma de tiempo de cada uno de sus intervalos, calculado con la diferencia del tiempo de finalizaciond con la tiempo de iniciacion de cada intervalo usando java.time.Duration method
  */
  public Duration TotalTime() {
    Duration duration = Duration.ZERO;
    for(int i = 0; i< intervals.size(); i++){
      Duration duration1 = Duration.between(intervals.get(i).getInitD(), intervals.get(i).getFinalD());
      duration=duration.plus(duration1);
    }
    return duration;
  }

  public DateTimeFormatter getInitD() {return initD;}
  public DateTimeFormatter getFinalD() {return finalD;}
  public void start(){
    Interval interval1 = new Interval(this);
    intervals.add(interval1);
    Clock.getInstance().addObserver(interval1);
    Clock.getInstance().startTimer();
    //crear thread
  }
  public void stop(String n){
    if(name.equals(n)){
      Clock.getInstance().stopTimer();
    }
  }

}
