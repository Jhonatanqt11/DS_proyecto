import java.time.format.DateTimeFormatter;

public abstract class Composite {
  private String name;

  public Composite(String name){
    super();
    this.name = name;
  }
  public abstract DateTimeFormatter TotalTime();
  public String getName(){
    return name;
  }
}
