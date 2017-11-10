package interfaces;


import android.content.Context;

import java.util.List;
import classes.Place;
import classes.Schedule;

public interface IPersistence {

    List<Schedule> getPlanningen(Context context, String filename);
    void addPlanning(Context context, List<Schedule> schedule, String filename);
    void addPlace(Schedule schedule, Place place);
}
