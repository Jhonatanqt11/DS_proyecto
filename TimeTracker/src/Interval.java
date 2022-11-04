
import org.json.JSONObject;

import java.io.FileWriter;
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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    String initialDateString = initialDate.format(formatter);
    String finalDateString = finalDate.format(formatter);
    System.out.println("interval:    " + initialDateString + "  " + finalDateString + "  " + duration.toString());
    task.update(initialDate, finalDate);
  }

  public Duration getDuration() {
    return duration;
  }
  public JSONObject save(){
    JSONObject interval = new JSONObject();
    interval.put("class","interval");
    interval.put("initialDate",initialDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    interval.put("finalDate",finalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    interval.put("duration",duration.toString());
    return interval;
  }
}
