import java.time.Duration;
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
