package persistencies;

import java.util.List;

import classes.Place;
import classes.Schedule;
import interfaces.IPersistence;

public class FileSerialization implements IPersistence
{
    @Override
    public List<Schedule> getPlanningen()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPlanning(Schedule schedule) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addPlace(Schedule schedule, Place place) { throw new UnsupportedOperationException(); }
}
