package classes;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.Duration;

public class Schedule
{
    private String name;
    private List<Place> places;
    private Date day;

    public Schedule(String name, List<Place> places, Date day) {
        this.name = name;
        this.places = places;
        this.day = day;
    }


}
