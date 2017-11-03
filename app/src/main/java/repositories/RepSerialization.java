package repositories;


import java.util.List;

import classes.Place;
import classes.Planning;
import interfaces.IPersistence;
import persistencies.FileSerialization;

public class RepSerialization {

    private IPersistence fileSerialization;
    private List<Planning> planningen;

    public RepSerialization()
    {
        fileSerialization = new FileSerialization();
    }

    private List<Planning> getPlanningenSortedASC()
    {
        planningen = fileSerialization.getPlanningen();

        throw new UnsupportedOperationException();
    }

    public List<Planning> getGetPlanningenSortedDESC()
    {
        throw new UnsupportedOperationException();
    }

    public void addPlanning(Planning planning)
    {
        fileSerialization.addPlanning(planning);
    }

    public void addPlace(Planning planning, Place place) {fileSerialization.addPlace(planning, place);}
}
