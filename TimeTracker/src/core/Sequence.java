package core;
public class Sequence {
  private static int id = 0;
  public static int getUniqueId(){
    id = id + 1;
    return id;
  }
}
