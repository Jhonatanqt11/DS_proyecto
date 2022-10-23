import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.time.Duration;

public class Project extends Composite {

  private List<Composite> Composites;

  public Project(String n) {
    super(n);
  }

  public Duration TotalTime() {

    for(int i=0; i<Composites.size(); i++)
    {

      if(Composites.get(i).esTask())
      {

      }
      else
      {

      }
    }
  }

  public void addComposite(Composite t) {
    Composites.add(t);
  }

  public boolean removeComposite(Composite t) {
    return Composites.remove(t);
    //Intento de if(*eliminado*) return true, if(*no eliminado*) return false
  }
}