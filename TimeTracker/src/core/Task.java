package core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//Task is the leaf class of the Activity tree.
// It is in charge of starting and stopping the intervals.

public class Task extends Activity {
  private final List<Interval> intervals;
  private boolean active;
  static final Logger logger = LoggerFactory.getLogger("Task");

  public Task(String n) {
    super(n);
    intervals = new ArrayList<>();
  }

  public void totalTime() {

    //Calculate the time by going through the list and adding the time of each element of the list.

    Duration duration1 = Duration.ZERO;
    for (Interval interval : intervals) {
      duration1 = duration1.plus(interval.getDuration());
    }
    duration = duration1;
  }

  public void start() {

    //Every time the task is started, an interval is created and the clock
    // assigns this new interval as an Observer, also this function starts the clock.

    logger.info(getName() + " starts");
    Interval interval1 = new Interval(this);
    intervals.add(interval1);
    Clock.getInstance().addObserver(interval1);
    Clock.getInstance().startTimer();
    active = true;
    project.start();
  } //When you want to stop working on the task, stop() will be called,
  //which will remove the Observer from the interval in which
  // it was working and will stop the clock counter.

  public void stop() {
    logger.info(getName() + " stops");
    intervals.get(intervals.size()-1).stopActivate();
    Clock.getInstance().deleteObserver(intervals.get(intervals.size() - 1));
    Clock.getInstance().stopTimer();
    active = false;
    project.stop();
  }

  @Override
  public String takeClass() {
    return "Task";
  }

  @Override
  public JSONObject save() {

    //Create a JSONArray to hold all the JSONObjects for each list item.
    // This is used to save the list of intervals.
    JSONArray intervalsJson = new JSONArray();
    //intervalsJson.put("class", "task");
    super.save();

    for (Interval interval : intervals) {
      intervalsJson.put(interval.save());
    }
    tree.put("intervals", intervalsJson);
    return tree;
  }

  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitTask(this);
  }

  @Override
  public Activity findActivityById(int id) {
    if (this.id == id)
      return this;
    else
      return null;
  }
  @Override
  public JSONObject toJson(int depth) {
    // depth not used here
    JSONObject json = new JSONObject();
    json.put("class", "task");
    super.toJson(json);
    json.put("active", active);
    if (depth>0) {
      JSONArray jsonIntervals = new JSONArray();
      for (Interval interval : intervals) {
        jsonIntervals.put(interval.toJson());
      }
      json.put("intervals", jsonIntervals);
    } else {
      json.put("intervals", new JSONArray());
    }
    return json;
  }

}
