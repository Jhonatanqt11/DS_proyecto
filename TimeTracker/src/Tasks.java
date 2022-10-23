import javax.crypto.Cipher;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Tasks extends Composite {
  private List<Interval> Interval;
  private DateTimeFormatter initD;
  private DateTimeFormatter finalD;

  public Tasks(String n){
    super(n);
  }
  public DateTimeFormatter TotalTime();
  public DateTimeFormatter getInitD() {return initD;}
  public DateTimeFormatter getFinalD() {return finalD;}
}
