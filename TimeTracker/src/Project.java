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


  /*el total time ha de calcular el tiempo total empleado en cada proyecto
   * el tiempo de un proyecto es un sumatorio de los tiempos de sus actividades, en caso de no haber ninguna actividad en el proyecto se devolverá un valor nulo*/
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
    //Crea un JSONArray para guardar todos los JSONObject de cada elemento de la lista. Esto sirve para poder guardas la lista de Activities.
    super.save();
    JSONArray list = new JSONArray();
    for(Activity activity : activities){
      list.put(activity.save());
    }
    tree.put("activities",list);
    return tree;
  }
}