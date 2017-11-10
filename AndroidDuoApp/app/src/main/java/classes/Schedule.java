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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
