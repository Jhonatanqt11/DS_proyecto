package core;
//import ch.qos.logback.classic.Logger;
import static java.lang.Math.round;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//Activity is the abstract class from which Task and Project classes will inherit.
//It has the attributes and methods that they share.
//With this class we are implementing the Composite pattern.

@SuppressWarnings("CheckStyle")
public abstract class Activity {
  static final Logger logger = LoggerFactory.getLogger("Activity");
  protected final String name;
  protected int id;
  protected LocalDateTime initialDate;
  protected LocalDateTime finalDate;
  protected Duration duration;
  protected Activity project;
  protected final JSONObject tree;
  protected List<String> tags;
  protected  boolean active;

  public Activity(String name) {
    this.name = name;
    this.initialDate = null;
    this.project = null;
    tree = new JSONObject();
    this.tags = new ArrayList<>();
    this.id = Sequence.getUniqueId();
    this.active = false;
    this.duration = Duration.ZERO;
  }

  public abstract void totalTime();

  public String getName() {return name;}

  protected int getId() {return id;}
  //This is the recursive update. Updates the end date and duration values, prints the object's data to the screen, and calls its parent's update recursively until it reaches the root

  public void update(LocalDateTime initialDate, LocalDateTime finalDate) {
    if (this.initialDate == null) {
      this.initialDate = initialDate;
    }
    this.finalDate = finalDate;
    totalTime();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    String initialDateString = initialDate.format(formatter);
    String finalDateString = finalDate.format(formatter);
    logger.info("activity:  " + name + "            " + initialDateString + "  " + finalDateString + "  " + round((double) duration.toMillis() / 1000));
    if (project != null) {
      project.update(initialDate, finalDate);
    }
  }

  public void setProject(Activity project) {
    this.project = project;
  }

  public Duration getDuration() {
    return duration;
  }

  public abstract String takeClass();

  public JSONObject save() {

    //It is used to save the attributes in a txt, in case the dates or the duration are null, a JSONObject.NULL is saved.

    tree.put("name", name);
    tree.put("id", id);
    tree.put("class", takeClass());
    if (initialDate == null) {
      logger.warn("Initial Date is null");
      tree.put("initialDate", JSONObject.NULL);
    } else {
      tree.put("initialDate", initialDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    }
    if (finalDate == null) {
      tree.put("finalDate", JSONObject.NULL);
    } else {
      tree.put("finalDate", finalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    }
    if (duration == null) {
      logger.warn("Duration is null");
      tree.put("duration", JSONObject.NULL);
    } else {
      tree.put("duration", duration.toString());
    }
    tree.put("active", active);
    return tree;
  }

  public void saver(String filename) throws IOException {

    //This function must be called from root, since it recursively traverses the tree from the current position to the leaves of the tree.

    FileWriter file = new FileWriter(filename);
    file.write(save().toString());
    file.close();
  }

  public void setTags(List<String> tags) {

    this.tags = tags;
  }

  public List<String> getTags() {
    return tags;
  }

  public abstract void acceptVisitor(Visitor visitor);
  public abstract Activity findActivityById(int id);

  protected static final DateTimeFormatter formatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public abstract JSONObject toJson(int depth); // added 16-dec-2022

  protected void toJson(JSONObject json) {
    json.put("id", id);
    json.put("name", name);
    json.put("initialDate", initialDate==null
        ? JSONObject.NULL : formatter.format(initialDate));
    json.put("finalDate", finalDate==null
        ? JSONObject.NULL : formatter.format(finalDate));
    json.put("duration", duration.toSeconds());
  }

}
