package repositories;

import domain.domain_classes.Place;
import domain.domain_classes.Planning;
import domain.interfaces.IPersistence;
import domain.persistencies.FileSerialization;

import java.util.Collections;
import java.util.List;

public class RepSerialization {

    private IPersistence fileSerialization;
    private List<Planning> planningen;

    public RepSerialization()
    {
        fileSerialization = new FileSerialization();
    }

    public List<Planning> getPlanningenSortedASC()
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
