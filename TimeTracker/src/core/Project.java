package core;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//Project is a node of the Activity tree. It can contain Tasks or other Projects.

public class Project extends Activity {

  //static Logger logger = LoggerFactory.getLogger("Project");

  private final List<Activity> activities;

  public Project(String n) {
    super(n);
    activities = new ArrayList<>();
  }


  /*the total time has to calculate the total time spent on each project
   * the time of a project is a sum of the times of its activities,
   * if there is no activity in the project a null value will be returned*/
  public void totalTime() {
    Duration duration1 = Duration.ZERO;
    for (Activity activity : activities) {
      if (activity.getDuration() != null) {
        duration1 = duration1.plus(activity.getDuration());
      }
    }
    duration = duration1;
  }

  public List<Activity> getActivities() {
    return activities;
  }

  public void addActivity(Activity activity) {
    activity.setProject(this);
    activities.add(activity);
  }

  @Override
  public String takeClass() {
    return "Project";
  }

  public JSONObject save() {
    //Create a JSONArray to hold all the JSONObjects for each list item.
    // This is used to save the list of Activities.
    super.save();
    JSONArray list = new JSONArray();
    for (Activity activity : activities) {
      list.put(activity.save());
    }
    tree.put("activities", list);
    return tree;
  }

  @Override
  public void acceptVisitor(Visitor visitor) {
    visitor.visitProject(this);
  }

  @Override
  public Activity findActivityById(int id) {
    if(this.id == id)
      return this;
    else{
      for (Activity activity : activities){
        if (activity.findActivityById(id) != null)
          return activity.findActivityById(id);
      }
      return null;
    }
  }
  @Override
  public JSONObject toJson(int depth) {
    JSONObject json = new JSONObject();
    json.put("class", "project");
    super.toJson(json);
    json.put("active", active);
    if (depth>0) {
      JSONArray jsonActivities = new JSONArray();
      for (Activity activity : activities) {
        jsonActivities.put(activity.toJson(depth - 1));
        // important: decrement depth
      }
      json.put("activities", jsonActivities);
    }
    return json;
  }
  public void start(){
    active = true;
    if(project != null)
      project.start();
  }
  public void stop(){
    active = false;
    if(project != null)
      project.stop();
  }
}
