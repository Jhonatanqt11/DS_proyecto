import java.util.ArrayList;
import java.util.List;

public class SearchByTag implements Visitor{
  private String tag;
  List<Activity> result;

  SearchByTag(String tag){
    this.tag = tag;
    this.result = new ArrayList<>();
  }
  @Override
  public void visitProject(Project project) {
    for (String tag : project.getTags()){
      if (tag.toLowerCase().equals(this.tag.toLowerCase()))
        result.add(project);
    }
    for (Activity activity : project.getActivities()) {
      activity.acceptVisitor(this);
    }
  }
  @Override
  public void visitTask(Task task) {
    for (String tag : task.getTags()){
      if(tag.toLowerCase().equals(this.tag.toLowerCase()))
        result.add(task);
    }
  }

  @Override
  public void visitInterval(Interval interval){
  }
  
  @Override
  public List<Activity> getResult() {return result;}
}
