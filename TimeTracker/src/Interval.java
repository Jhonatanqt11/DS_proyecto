
import org.json.JSONObject;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Math.round;

//Interval is the class that has the Observer role. It is in charge of starting the recursive update of the duration time that goes all the way to the root.

public class Interval implements Observer {
  private final Task task;
  private LocalDateTime initialDate;
  private LocalDateTime finalDate;
  private Duration duration;

  public Interval(Task t){
    task = t;
    initialDate = null;

  }
  //Every time the clock ticks, the update of the intervals that are watching it will be called. It will update its end date and duration, print its data to the screen, and call the recursive update of its Task parent.
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
    System.out.println("interval:                  " + initialDateString + "  " + finalDateString + "  " + round((double)duration.toMillis()/1000));
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

  public void acceptVisitor(Visitor visitor){
    visitor.visitInterval(this);
  }
}
