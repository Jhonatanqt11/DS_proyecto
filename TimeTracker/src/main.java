import java.io.IOException;

public class main {
  private static void sampleTree() throws InterruptedException {

  }
  private static void countingTime() {



  }
 //private static void notSoSimpleTest() { ... }
  public static void main(String[] args) throws IOException {
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
    //System.exit(0);
  }
}
