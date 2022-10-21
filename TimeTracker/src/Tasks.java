import java.time.format.DateTimeFormatter;
import java.util.List;

public class Tasks extends Project{
  private List<Interval> Interval;
  private DateTimeFormatter initD;
  private DateTimeFormatter finalD;

  public Tasks(String n);
  public DateTimeFormatter TotalTime();
  public DateTimeFormatter getInitD() {return initD;}
  public DateTimeFormatter getFinalD() {return finalD;}
}
