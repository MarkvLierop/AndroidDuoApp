package domain.interfaces;

import domain.domain_classes.Place;
import domain.domain_classes.Planning;
import java.util.List;

public interface IPersistence {

    List<Planning> getPlanningen();
    void addPlanning(Planning planning);
    void addPlace(Planning planning, Place place);
}
