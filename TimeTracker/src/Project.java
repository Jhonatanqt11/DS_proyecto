import java.util.List;
import java.time.Duration;

public class Project extends Activity {

  private List<Activity> Composites;

  public Project(String n) {
    super(n);
    activities = new ArrayList<Activity>();
  }

  public void totalTime() {
    Duration duration1 = Duration.ZERO;
    for(int i=0; i<activities.size(); i++)
    {
      tempsTotal = tempsTotal.plus(Composites.get(i).TotalTime());
    }

    return tempsTotal;
  }

  public void addActivity(Activity t) {
    Composites.add(t);
  }

  public boolean removeActivity(Activity t) {
    return Composites.remove(t);
    //Intento de if(*eliminado*) return true, if(*no eliminado*) return false
  }
}