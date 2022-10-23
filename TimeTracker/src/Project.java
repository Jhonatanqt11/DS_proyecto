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

    Duration tempsTotal = Duration.ZERO;

    for(int i=0; i<Composites.size(); i++)
    {
      tempsTotal = tempsTotal.plus(Composites.get(i).TotalTime());
    }

    return tempsTotal;
  }

  public void addComposite(Composite t) {
    Composites.add(t);
  }

  public boolean removeComposite(Composite t) {
    return Composites.remove(t);
    //Intento de if(*eliminado*) return true, if(*no eliminado*) return false
  }
}