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
    private List<String> placeIds;
    private Date day;

    public Schedule(String name, Date day) {
        this.name = name;
        this.day = day;

        placeIds = new ArrayList<>();
    }

    public void addPlace(String place)
    {
        placeIds.add(place);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPlaces() {
        return placeIds;
    }

    public void setPlaces(List<String> places) {
        this.placeIds = places;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
