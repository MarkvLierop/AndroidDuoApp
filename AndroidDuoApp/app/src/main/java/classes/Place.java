package classes;

import android.graphics.Bitmap;
import android.media.Image;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.xml.datatype.Duration;

public class Place {

    private Bitmap placeImage;
    private String locationX;
    private String locationY;
    private String placeID;
    private String placeName;
    private String streetName;
    private String cityName;
    private String phoneNumber;
    private int stars;
    private int distanceInM;
    private Timestamp openTime;
    private Timestamp closingTime;

    private Duration TimeThere;


    public Place()
    {

    }


    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Timestamp getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Timestamp openTime) {
        this.openTime = openTime;
    }

    public Timestamp getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Timestamp closingTime) {
        this.closingTime = closingTime;
    }

    public int getDistanceInM() {
        return distanceInM;
    }

    public void setDistanceInM(int distanceInM) {
        this.distanceInM = distanceInM;
    }

    public Bitmap getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(Bitmap placeImage) {
        this.placeImage = placeImage;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }
}
