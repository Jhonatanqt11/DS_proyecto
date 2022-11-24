//import ch.qos.logback.classic.Logger;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchByTag implements Visitor {
  private final String tag;
  private final List<Activity> result;
  static final Logger logger = LoggerFactory.getLogger("SearchByTag");

  SearchByTag(String tag) {
    this.tag = tag;
    this.result = new ArrayList<>();
  }

  @Override
  public void visitProject(Project project) {
    for (String tag : project.getTags()) {
      if (tag.equalsIgnoreCase(this.tag)) {
        result.add(project);
      }
    }
    for (Activity activity : project.getActivities()) {
      activity.acceptVisitor(this);
    }
  }

  @Override
  public void visitTask(Task task) {
    for (String tag : task.getTags()) {
      if (tag.equalsIgnoreCase(this.tag)) {
        result.add(task);
      }
    }
  }

  //@Override
  //public void visitInterval(Interval interval){
  //}
  
  @Override
  public void printResult() {
    for (Activity activity : result) {
      logger.info(activity.getName());
    }
  }
}
