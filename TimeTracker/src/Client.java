import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Client {

  private static void countingTime() throws IOException {
    System.out.println("Start of test");

    Project root = new Project("root");
    Task transportation = new Task("transportation");
    Project softwareDesign = new Project("software design");
    Project softwareTesting = new Project("software testing");
    Project databases = new Project("databases");
    Project problems = new Project("problems");
    Project timeTracker = new Project("time tracker");
    Task firstList = new Task("first list");
    Task secondList = new Task("second list");
    Task readHandout = new Task("read handout");
    Task firstMilestone = new Task("first milestone");
    root.addActivity(transportation);
    root.addActivity(softwareDesign);
    root.addActivity(softwareTesting);
    root.addActivity(databases);
    softwareDesign.addActivity(problems);
    softwareDesign.addActivity(timeTracker);
    problems.addActivity(firstList);
    problems.addActivity(secondList);
    timeTracker.addActivity(readHandout);
    timeTracker.addActivity(firstMilestone);

    transportation.start();
    try {
      Thread.sleep(6000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    transportation.stop();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    firstList.start();
    try {
      Thread.sleep(6000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    secondList.start();
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    firstList.stop();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    secondList.stop();
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    transportation.start();
    try {
      Thread.sleep(4000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    transportation.stop();
    root.saver("save.txt");
    System.out.println("End of test");
  }
 //private static void notSoSimpleTest() { ... }
 private static void searchTag(){
   Project root = new Project("root");
   Task transportation = new Task("transportation");
   Project softwareDesign = new Project("software design");
   Project softwareTesting = new Project("software testing");
   Project databases = new Project("databases");
   Project problems = new Project("problems");
   Project timeTracker = new Project("time tracker");
   Task firstList = new Task("first list");
   Task secondList = new Task("second list");
   Task readHandout = new Task("read handout");
   Task firstMilestone = new Task("first milestone");
   root.addActivity(transportation);
   root.addActivity(softwareDesign);
   root.addActivity(softwareTesting);
   root.addActivity(databases);
   softwareDesign.addActivity(problems);
   softwareDesign.addActivity(timeTracker);
   problems.addActivity(firstList);
   problems.addActivity(secondList);
   timeTracker.addActivity(readHandout);
   timeTracker.addActivity(firstMilestone);

   List <String> tag = Arrays.asList("java","flutter");
   softwareDesign.setTags(tag);
   tag = Arrays.asList("c++","Java","python");
   softwareTesting.setTags(tag);
   tag = Arrays.asList("SQL","python","C++");
   databases.setTags(tag);
   tag = Arrays.asList("java");
   firstList.setTags(tag);
   tag = Arrays.asList("Dart");
   secondList.setTags(tag);
   tag = Arrays.asList("Java","IntelliJ");
   firstMilestone.setTags(tag);

   Visitor visitor = new SearchByTag("c++");
   root.acceptVisitor(visitor);
   visitor.printResult();
 }
  public static void main(String[] args) throws IOException {

    countingTime();
    searchTag();
  }
}
