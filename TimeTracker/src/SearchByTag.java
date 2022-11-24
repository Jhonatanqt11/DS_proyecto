


//import ch.qos.logback.classic.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchByTag implements Visitor{
  private String tag;
  private List<Activity> result;
  static Logger logger = LoggerFactory.getLogger("SearchByTag");


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
  public void printResult() {
    for(Activity activity : result){
      logger.info(activity.getName());
    }
  }
}
