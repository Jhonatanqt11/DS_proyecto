import java.time.format.DateTimeFormatter;

public class Interval {
  private DateTimeFormatter initD;
  private DateTimeFormatter finalD;

  public Interval(DateTimeFormatter init, DateTimeFormatter fin){
    initD=init;
    finalD = fin;
  }
  public DateTimeFormatter getInitD() {return initD;}
  public DateTimeFormatter getFinalD() {return finalD;}
}
