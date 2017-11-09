package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.Duration;

public class Schedule implements Serializable
{
    private String name;
    private List<Place> places;
    private Date day;

    public Schedule(String name, Date day) {
        this.name = name;
        this.day = day;

        places = new ArrayList<>();
    }

    public void addPlace(Place place)
    {
        places.add(place);
    }
}
