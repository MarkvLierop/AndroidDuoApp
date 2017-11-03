package interfaces;


import java.util.List;
import classes.Place;
import classes.Schedule;

public interface IPersistence {

    List<Schedule> getPlanningen();
    void addPlanning(Schedule schedule);
    void addPlace(Schedule schedule, Place place);
}
