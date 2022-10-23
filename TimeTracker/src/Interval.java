import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Interval {
  private LocalDateTime initD;
  private LocalDateTime finalD;

  public Interval(LocalDateTime init, LocalDateTime fin){
    initD=init;
    finalD = fin;
  }
  public LocalDateTime getInitD() {return initD;}
  public LocalDateTime getFinalD() {return finalD;}
}
