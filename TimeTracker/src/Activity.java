import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Activity {
  protected String name;
  protected LocalDateTime initialDate = null;
  protected LocalDateTime finalDate;
  protected Duration duration;
  protected Activity project = null;

  public Activity(String name){
    super();
    this.name = name;
  }
  public abstract void totalTime();
  public String getName(){
    return name;
  }

  public void update(LocalDateTime initialDate, LocalDateTime finalDate){
    if (this.initialDate == null)
    {
      this.initialDate = initialDate;
    }
    this.finalDate = finalDate;
    totalTime();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    String initialDateString = initialDate.format(formatter);
    String finalDateString = finalDate.format(formatter);
    System.out.println("activity:  "+ name + "  " + initialDateString + "  " + finalDateString + "  " + duration.toString() );
    if(project != null) {
      project.update(initialDate, finalDate);
    }
  }

  public void setProject(Activity project) {
    this.project = project;
  }

  public Duration getDuration() {
    return duration;
  }
}
