import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Activity {
  protected String name;
  protected LocalDateTime initialDate;
  protected LocalDateTime finalDate;
  protected Duration duration;
  protected Activity project;
  protected JSONObject tree;

  public Activity(String name){
    super();
    this.name = name;
    this.initialDate = null;
    this.project = null;
    tree = new JSONObject();
  }
  public abstract void totalTime();
  public String getName(){
    return name;
  }

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
    System.out.println("activity:  "+ name + "  " + initialDateString + "  " + finalDateString + "  " + duration.toString() );
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
    FileWriter file = new FileWriter(filename);
    file.write(save().toString());
    file.close();
  }
}
