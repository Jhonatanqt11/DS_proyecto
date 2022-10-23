import javax.crypto.Cipher;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Tasks extends Composite {
  private List<Interval> interval;
  private DateTimeFormatter initD;
  private DateTimeFormatter finalD;

  public Tasks(String n){
    super(n);
    interval = new ArrayList<Interval>();
  }
  public Duration TotalTime() {
    Duration duration = Duration.ZERO;
    for(int i = 0; i< interval.size(); i++){
      Duration duration1 = Duration.between(interval.get(i).getInitD(), interval.get(i).getFinalD());
      duration=duration.plus(duration1);
    }
    return duration;
  }

  public DateTimeFormatter getInitD() {return initD;}
  public DateTimeFormatter getFinalD() {return finalD;}
  public void addInterval(Interval i){
    interval.add(i);
  }

}
