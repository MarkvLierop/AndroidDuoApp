package persistencies;

import java.util.List;

import classes.Place;
import classes.Planning;
import interfaces.IPersistence;

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
