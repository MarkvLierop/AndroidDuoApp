package domain.repositories;

import domain.domain_classes.Planning;
import domain.interfaces.IPersistence;
import domain.persistencies.FileSerialization;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

        throw new NotImplementedException();
    }

    public List<Planning> getGetPlanningenSortedDESC()
    {
        throw new NotImplementedException();
    }

    public void addPlanning(Planning planning)
    {
        fileSerialization.addPlanning(planning);
    }
}
