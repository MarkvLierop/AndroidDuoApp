package classes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.media.Image;
import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class GoogleApi
{
    private final static String APIKEY = "AIzaSyAekDAiLYoKsFRCFfjFoEb1XoVYnxgIU9g";
    private ArrayList<Place> places;

    public GoogleApi()
    {
        ignoreThreadRequirement();
    }

    private Place getPlaceData(Place place) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/details/json?" +
                                    "placeid="+place.getPlaceID()+"&" +
                                    "key=" + APIKEY;

        JSONObject object = parseJSON(googleRequestURL);
        JSONArray res = object.getJSONObject("result").getJSONArray("address_components");

        for (int i = 0; i < res.length();i++)
        {
            place.setStreetName(res.getJSONObject(1).getString("long_name"));
            place.setCityName(res.getJSONObject(3).getString("long_name"));
        }

        URL url = new URL(object.getJSONObject("result").getString("icon"));
        place.setPlaceImage(BitmapFactory.decodeStream(url.openConnection().getInputStream()));
        place.setPhoneNumber(object.getJSONObject("result").getString("formatted_phone_number"));
        place.setPlaceName(object.getJSONObject("result").getString("name"));

        return place;
    }

    public List<Place> getNearbyPlacesRestaurants(String locationX, String locationY) throws IOException, JSONException
    {
        places = new ArrayList<>();

        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                                    "location="+locationX + ","+ locationY+"&"+
                                    "radius=5000&" +
                                    "type=restaurant&" +
                                    "key=" + APIKEY;

        JSONObject object = parseJSON(googleRequestURL);
        assignNearbyPlaceData(object);

        return places;
    }

    public List<Place> getNearbyPlacesMuseums(String locationX, String locationY) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                "location="+locationX + ","+ locationY+"&"+
                "radius=5000&" +
                "type=museum&" +
                "key=" + APIKEY;

        JSONObject object = parseJSON(googleRequestURL);
        assignNearbyPlaceData(object);

        return places;
    }

    public List<Place> getNearbyPlacesCafes(String locationX, String locationY) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                "location="+locationX + ","+ locationY+"&"+
                "radius=5000&" +
                "type=cafe&" +
                "key=" + APIKEY;

        JSONObject object = parseJSON(googleRequestURL);
        assignNearbyPlaceData(object);

        return places;
    }

    public List<Place> getNearbyPlacesHealth(String locationX, String locationY) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                "location="+locationX + ","+ locationY+"&"+
                "radius=5000&" +
                "type=health&" +
                "key=" + APIKEY;

        JSONObject object = parseJSON(googleRequestURL);
        assignNearbyPlaceData(object);

        return places;
    }

    public JSONObject parseJSON(String googleRequestURL) throws IOException, JSONException
    {
        // String om route te genereren
        //String Url = "https://maps.googleapis.com/maps/api/directions/json?origin=Bakel&destination=Helmond&key=" + APIKEY;

        StringBuilder jsonResults = new StringBuilder();
        URL url = new URL(googleRequestURL);
        Log.d("tag", googleRequestURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStreamReader in = new InputStreamReader(conn.getInputStream());

        // Load the results into a StringBuilder
        int read;
        char[] buff = new char[1024];
        while ((read = in.read(buff)) != -1) {
            jsonResults.append(buff, 0, read);
        }
        conn.disconnect();

        return new JSONObject(jsonResults.toString());


    }

    private void assignNearbyPlaceData(JSONObject object) throws JSONException, IOException
    {

        for (int i = 0; i < object.getJSONArray("results").length();i++)
        {
            Place place = new Place();

            JSONObject res = object.getJSONArray("results").getJSONObject(i).getJSONObject("geometry").getJSONObject("location");

            place.setLocationX(res.getString("lat"));
            place.setLocationY(res.getString("lng"));
            place.setPlaceID(object.getJSONArray("results").getJSONObject(i).getString("place_id"));

            place = getPlaceData(place);
            places.add(place);

            if (i == 10)
            {
                break;
            }
        }
    }

    // Vanwege instellingen moet een URL request in een thread gebeuren. Dit zorgt ervoor dat er geen thread nodig is.
    private void ignoreThreadRequirement()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
