package core;
import static java.lang.Math.round;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
import java.util.Observer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//Interval is the class that has the Observer role.
//It is in charge of starting the recursive update of the
//duration time that goes all the way to the root.

public class Interval implements Observer {
  private final Task task;
  private LocalDateTime initialDate;
  private LocalDateTime finalDate;
  private int id;
  private Duration duration;
  private boolean active;
  static final Logger logger = LoggerFactory.getLogger("Interval");
  private static final DateTimeFormatter formatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


  public Interval(Task t) {
    task = t;
    initialDate = null;
    active = true;
    id = Sequence.getUniqueId();
  }

  //Every time the clock ticks, the update of the intervals that are watching it will be called.
  // It will update its end date and duration, print its data to the screen,
  // and call the recursive update of its Task parent.
  @Override
  public void update(Observable observable, Object clock) {
    Clock clockInterval = (Clock) clock;
    finalDate = clockInterval.getTime();
    if (initialDate == null) {
      initialDate = finalDate.minusSeconds(2);
    }
    duration = Duration.between(initialDate, finalDate);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    String initialDateString = initialDate.format(formatter);
    String finalDateString = finalDate.format(formatter);
    logger.info("interval: " + initialDateString + "  " + finalDateString
        + "  " + round((double) duration.toMillis() / 1000));
    task.update(initialDate, finalDate);
  }
  public void stopActivate() {active = false;}

  public Duration getDuration() {
    return duration;
  }

  public JSONObject save() {
    JSONObject interval = new JSONObject();
    interval.put("class", "interval");
    interval.put("id", id);
    interval.put("initialDate",
        initialDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    interval.put("finalDate", finalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    interval.put("duration", duration.toString());
    interval.put("active", active);
    return interval;
  }

  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("class", "interval");
    json.put("id", id);
    json.put("initialDate", initialDate==null
        ? JSONObject.NULL : formatter.format(initialDate));
    json.put("finalDate", finalDate==null
        ? JSONObject.NULL : formatter.format(finalDate));
    json.put("duration", duration.toSeconds());
    json.put("active", active);
    return json;
  }
  //public void acceptVisitor(Visitor visitor) {
  //  visitor.visitInterval(this);
  //}
}
