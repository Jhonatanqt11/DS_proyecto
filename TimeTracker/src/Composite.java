import java.time.Duration;
import java.time.format.DateTimeFormatter;

public abstract class Composite {
  private String name;

  public Composite(String name){
    super();
    this.name = name;
  }
  public abstract Duration TotalTime();
  public String getName(){
    return name;
  }
}
