import java.util.List;
import java.time.Duration;

//Un proyecto puede estar frmado de otros proyectos y de una o mas tareas
public class Project extends Activity {

  private List<Activity> Composites;

  public Project(String n) {
    super(n);
  }


  /*IMPORTANTE:
  TotalTime debe calcular el tiempo total de un proyecto con recursividad sumando el TiempoTotal de cada una de sus actividades, ya sean otros Proyectos o Tasks*/
  public Duration TotalTime() {

    Duration tempsTotal = Duration.ZERO;

    for(int i=0; i<Composites.size(); i++)
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