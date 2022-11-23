
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

import org.json.*;

//Project is a node of the Activity tree. It can contain Tasks or other Projects.

public class Project extends Activity {

  private final List<Activity> activities;

  public Project(String n) {
    super(n);
    activities = new ArrayList<>();
  }


  /*the total time has to calculate the total time spent on each project
   * the time of a project is a sum of the times of its activities, if there is no activity in the project a null value will be returned*/
  public void totalTime() {
    Duration duration1 = Duration.ZERO;
    for (Activity activity : activities) {
      if (activity.getDuration() != null)
        duration1 = duration1.plus(activity.getDuration());
    }
    duration = duration1;
  }
  public List<Activity> getActivities(){return activities;}

  public void addActivity(Activity activity) {
    activity.setProject(this);
    activities.add(activity);
  }

  @Override
  public String takeClass() {
    return "Project";
  }

  public JSONObject save() {
    //Create a JSONArray to hold all the JSONObjects for each list item. This is used to save the list of Activities.
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
}
