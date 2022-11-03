import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Task extends Activity {
  private List<Interval> intervals;

  public Task(String n){
    super(n);
    intervals = new ArrayList<Interval>();
  }
  public void totalTime() {
    Duration duration1 = Duration.ZERO;
    for(int i = 0; i<intervals.size() ; i++){
      duration1=duration1.plus(intervals.get(i).getDuration());
    }
    duration = duration1;
  }

  public LocalDateTime getInitialDate() {return initialDate;}
  public LocalDateTime getFinalDate() {return finalDate;}
  public void start(){
    Interval interval1 = new Interval(this);
    intervals.add(interval1);
    Clock.getInstance().addObserver(interval1);
    Clock.getInstance().startTimer();
    //crear thread
  }
  public void stop(){
    Clock.getInstance().stopTimer();
  }
}
