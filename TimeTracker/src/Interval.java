
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer {
  private Task task;
  private LocalDateTime initD;
  private LocalDateTime finalD;
  private Duration duration;

  public Interval(Task t){
    task = t;
    initD = LocalDateTime.now();

  }
  public LocalDateTime getInitD() {return initD;}
  public LocalDateTime getFinalD() {return finalD;}
  @Override
  public void update(Observable observable, Object clock){
    Clock clockInterval = (Clock) clock;
    finalD = clockInterval.getTime();
    duration = Duration.between(initD,finalD);
  }
}
