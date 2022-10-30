import java.time.Duration;

public abstract class Activity {
  private String name;

  public Activity(String name){
    super();
    this.name = name;
  }
  public abstract Duration TotalTime();
  public String getName(){
    return name;
  }
}
