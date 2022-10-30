public class User {

  private Project Projecte[];

  public static void main() throws InterruptedException {
    Task t0 =new Task("transportation");
    t0.start();
    Thread.sleep(6000);
    t0.stop("transportation");
  }
}