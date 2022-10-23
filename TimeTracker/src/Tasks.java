import javax.crypto.Cipher;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Tasks extends Composite {
  private List<Interval> Interval;
  private DateTimeFormatter initD;
  private DateTimeFormatter finalD;

  public Tasks(String n){
    super(n);
  }
  public Duration TotalTime() {
    Duration duration = Duration.between(Interval.get(0).getInitD(), Interval.get(0).getFinalD());
    for(int i = 1; i< Interval.size(); i++){
      Duration duration1 = Duration.between(Interval.get(i).getInitD(), Interval.get(i).getFinalD());
      duration=duration.plus(duration1);
    }
    return duration;
  }

  public DateTimeFormatter getInitD() {return initD;}
  public DateTimeFormatter getFinalD() {return finalD;}
}
