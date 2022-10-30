public class main {
  private static void simpleTest() throws InterruptedException {
    Task t0 = new Task("study");
    t0.start();
    Thread.sleep(2000);
    t0.stop("study");
  }
 //private static void notSoSimpleTest() { ... }
  public static void main(String[] args) {
    try {
      simpleTest();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
// notSoSimpleTest();
  }
}
