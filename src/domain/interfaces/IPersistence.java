package domain.interfaces;

import domain.domain_classes.Planning;
import java.util.List;

public interface IPersistence {

    List<Planning> getPlanningen();
    void addPlanning(Planning planning);
}
