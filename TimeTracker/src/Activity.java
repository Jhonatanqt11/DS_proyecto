
import org.json.JSONObject;


import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

//Activity is the abstract class from which Task and Project classes will inherit. It has the attributes and methods that they share. With this class we are implementing the Composite pattern.

public abstract class Activity {
  protected final String name;
  protected LocalDateTime initialDate;
  protected LocalDateTime finalDate;
  protected Duration duration;
  protected Activity project;
  protected final JSONObject tree;
  protected List<String> tags;

  public Activity(String name){
    this.name = name;
    this.initialDate = null;
    this.project = null;
    tree = new JSONObject();
    this.tags = new ArrayList<>();
  }
  public abstract void totalTime();
  public String getName(){
    return name;
  }

  //This is the recursive update. Updates the end date and duration values, prints the object's data to the screen, and calls its parent's update recursively until it reaches the root.
  public void update(LocalDateTime initialDate, LocalDateTime finalDate){
    if (this.initialDate == null)
    {
      this.initialDate = initialDate;
    }
    this.finalDate = finalDate;
    totalTime();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    String initialDateString = initialDate.format(formatter);
    String finalDateString = finalDate.format(formatter);
    System.out.println("activity:  "+ name + "            " + initialDateString + "  " + finalDateString + "  " + round((double)duration.toMillis()/1000));
    if(project != null) {
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
  public JSONObject save(){
    //It is used to save the attributes in a txt, in case the dates or the duration are null, a JSONObject.NULL is saved.
    tree.put("name",name);
    tree.put("class",takeClass());
    if(initialDate == null){
      tree.put("initialDate",JSONObject.NULL);
    }
    else
      tree.put("initialDate",initialDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    if(finalDate == null){
      tree.put("finalDate",JSONObject.NULL);
    }
    else
      tree.put("finalDate",finalDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss")));
    if(duration == null){
      tree.put("duration", JSONObject.NULL);
    }
    else
      tree.put("duration",duration.toString());
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
}
