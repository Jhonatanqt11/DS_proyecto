
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer {
  private Task task;
  private LocalDateTime initialDate;
  private LocalDateTime finalDate;
  private Duration duration;

  public Interval(Task t){
    task = t;
    initialDate = null;

  }
  public LocalDateTime getInitialDate() {return initialDate;}
  public LocalDateTime getFinalDate() {return finalDate;}
  @Override
  public void update(Observable observable, Object clock){
    Clock clockInterval = (Clock) clock;
    finalDate = clockInterval.getTime();
    if (initialDate == null)
      initialDate = finalDate.minusSeconds(2);
    duration = Duration.between(initialDate,finalDate);

    task.update(initialDate, finalDate);
  }

  public Duration getDuration() {
    return duration;
  }
}
