package interfaces;


import java.util.List;
import classes.Place;
import classes.Planning;

public interface IPersistence {

    List<Planning> getPlanningen();
    void addPlanning(Planning planning);
    void addPlace(Planning planning, Place place);
}
