import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

import org.json.*;

public class Project extends Activity {

  private List<Activity> activities;
  public Project(String n) {
    super(n);
    activities = new ArrayList<Activity>();
  }

  public void totalTime() {
    Duration duration1 = Duration.ZERO;
    for(int i=0; i<activities.size(); i++)
    {
      if(activities.get(i).getDuration() != null)
        duration1 = duration1.plus(activities.get(i).getDuration());
    }
    duration = duration1;
  }

  public void addActivity(Activity activity) {
    activity.setProject(this);
    activities.add(activity);
  }

  public boolean removeActivity(Activity activity) {
    return activities.remove(activity);
  }

  @Override
  public String takeClass() {
    return "Project";
  }
  public JSONObject save(){
    super.save();
    JSONArray list = new JSONArray();
    for(Activity activity : activities){
      list.put(activity.save());
    }
    tree.put("activities",list);
    return tree;
  }
}