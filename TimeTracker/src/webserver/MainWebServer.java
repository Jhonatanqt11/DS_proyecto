package webserver;

import core.Activity;
import core.Clock;
import core.Project;
import core.Task;

public class MainWebServer {
  public static void main(String[] args) {
    webServer();
  }

  public static void webServer() {
    final Activity root = makeTreeCourses();
    // implement this method that returns the tree of
    // appendix A in the practicum handout

    // start your clock

    new WebServer(root);
  }

  private static Activity makeTreeCourses() {
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
    return root;
  }
}