package persistencies;

import domain.domain_classes.Place;
import domain.domain_classes.Planning;
import domain.interfaces.IPersistence;

import java.util.List;

public class FileSerialization implements IPersistence
{
    @Override
    public List<Planning> getPlanningen()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPlanning(Planning planning) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPlace(Planning planning, Place place) { throw new UnsupportedOperationException(); }
}
