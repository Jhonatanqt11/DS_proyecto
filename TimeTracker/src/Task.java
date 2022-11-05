import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Task extends Activity {
  private List<Interval> intervals;

  public Task(String n){
    super(n);
    intervals = new ArrayList<Interval>();
  }
  public void totalTime() {
    //Calcula el tiempo de recorriendo la lista y sumando el tiempo de cada elemento de ella.
    Duration duration1 = Duration.ZERO;
    for(int i = 0; i<intervals.size() ; i++){
      duration1=duration1.plus(intervals.get(i).getDuration());
    }
    duration = duration1;
  }

  public LocalDateTime getInitialDate() {return initialDate;}
  public LocalDateTime getFinalDate() {return finalDate;}
  public void start(){
    //Cada vez que se hace un start a la task, se crea un intervalo y al clock asigna como observer este nuevo intervalo, ademas esta función inicia el clock.
    System.out.println(getName() + " starts");
    Interval interval1 = new Interval(this);
    intervals.add(interval1);
    Clock.getInstance().addObserver(interval1);
    Clock.getInstance().startTimer();
  }

  //Cuando se quiera parar de trabajar en la tarea, se llamarà a stop() que quitará el Observer del intervalo en que se estaba trabajando y parará el contador del reloj
  public void stop(){
    System.out.println(getName() + " stops");
    Clock.getInstance().deleteObserver(intervals.get(intervals.size()-1));
    Clock.getInstance().stopTimer();
  }

  @Override
  public String takeClass() {
    return "Task";
  }

  @Override
  public JSONObject save() {
    //Crea un JSONArray para guardar todos los JSONObject de cada elemento de la lista. Esto sirve para poder guardas la lista de intervalos.
    super.save();
    JSONArray intervalsjson = new JSONArray();
    for (Interval interval : intervals){
      intervalsjson.put(interval.save());
    }
    tree.put("intervals",intervalsjson);
    return tree;
  }
}
