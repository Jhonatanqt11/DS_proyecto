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
    root.addActivity(transportation);
    root.addActivity(softwareDesign);
    root.addActivity(softwareTesting);
    root.addActivity(databases);
    Project problems = new Project("problems");
    softwareDesign.addActivity(problems);
    Project timeTracker = new Project("time tracker");
    softwareDesign.addActivity(timeTracker);
    Task firstList = new Task("first list");
    problems.addActivity(firstList);
    Task secondList = new Task("second list");
    problems.addActivity(secondList);
    Task readHandout = new Task("read handout");
    timeTracker.addActivity(readHandout);
    Task firstMilestone = new Task("first milestone");
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

  private static void searchTag() {
    Project root = new Project("root");
    Task transportation = new Task("transportation");
    Project softwareDesign = new Project("software design");
    Project softwareTesting = new Project("software testing");
    Project databases = new Project("databases");
    root.addActivity(transportation);
    root.addActivity(softwareDesign);
    root.addActivity(softwareTesting);
    root.addActivity(databases);
    Project problems = new Project("problems");
    softwareDesign.addActivity(problems);
    Project timeTracker = new Project("time tracker");
    softwareDesign.addActivity(timeTracker);
    Task firstList = new Task("first list");
    problems.addActivity(firstList);
    Task secondList = new Task("second list");
    problems.addActivity(secondList);
    Task readHandout = new Task("read handout");
    timeTracker.addActivity(readHandout);
    Task firstMilestone = new Task("first milestone");
    timeTracker.addActivity(firstMilestone);

    List<String> tag = Arrays.asList("java", "flutter");
    softwareDesign.setTags(tag);
    tag = Arrays.asList("c++", "Java", "python");
    softwareTesting.setTags(tag);
    tag = Arrays.asList("SQL", "python", "C++");
    databases.setTags(tag);
    tag = List.of("java");
    firstList.setTags(tag);
    tag = List.of("Dart");
    secondList.setTags(tag);
    tag = Arrays.asList("Java", "IntelliJ");
    firstMilestone.setTags(tag);

    Visitor testVisitor1 = new SearchByTag("java");
    root.acceptVisitor(testVisitor1);
    testVisitor1.printResult();
    Visitor testVisitor2 = new SearchByTag("JAVA");
    root.acceptVisitor(testVisitor2);
    testVisitor2.printResult();
    Visitor testVisitor3 = new SearchByTag("intellij");
    root.acceptVisitor(testVisitor3);
    testVisitor3.printResult();
    Visitor testVisitor4 = new SearchByTag("C++");
    root.acceptVisitor(testVisitor4);
    testVisitor4.printResult();
    Visitor testVisitor5 = new SearchByTag("python");
    root.acceptVisitor(testVisitor5);
    testVisitor5.printResult();
 }

  public static void main(String[] args) throws IOException {
    countingTime();
    searchTag();
  }

}
