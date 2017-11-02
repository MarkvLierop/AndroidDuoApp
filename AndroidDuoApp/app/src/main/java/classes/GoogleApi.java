package classes;

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
import java.util.List;

public class GoogleApi
{
    private final static String APIKEY = "AIzaSyAekDAiLYoKsFRCFfjFoEb1XoVYnxgIU9g";

    public GoogleApi()
    {
        ignoreThreadRequirement();
    }

    public Place getPlaceData(String placeID) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/details/json?" +
                                    "placeid="+placeID+"&" +
                                    "key=" + APIKEY;
        JSONObject object = parseJSON(googleRequestURL);

        throw new UnsupportedOperationException();
    }

    public List<Place> getPlacesSortedByRatingASC()
    {
        throw new UnsupportedOperationException();
    }
    public List<Place> getPlacesSortedByRatingDESC()
    {
        throw new UnsupportedOperationException();
    }
    public List<Place> getPlacesSortedByDistancesASC()
    {
        throw new UnsupportedOperationException();
    }
    public List<Place> getPlacesSortedByDistancesDESC()
    {
        throw new UnsupportedOperationException();
    }

    public List<Place> getNearbyPlacesRestaurants(String locationX, String locationY) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                                    "location="+locationX + ","+ locationY+"&"+
                                    "radius=5000&" +
                                    "type=restaurant&" +
                                    "key=" + APIKEY;
        JSONObject object = parseJSON(googleRequestURL);

        for (int i = 0; i < object.getJSONArray("results").length();i++)
        {
            JSONObject res = object.getJSONArray("results").getJSONObject(i).getJSONArray("geometry").getJSONObject(0);
            System.out.println(res.getJSONObject("location").getString("lat"));
            System.out.println(res.getJSONObject("location").getString("lng"));

        }
        throw new UnsupportedOperationException();
    }

    public List<Place> getNearbyPlacesMuseums(String locationX, String locationY) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                "location="+locationX + ","+ locationY+"&"+
                "radius=5000&" +
                "type=museum&" +
                "key=" + APIKEY;
        JSONObject object = parseJSON(googleRequestURL);

        throw new UnsupportedOperationException();
    }

    public List<Place> getNearbyPlacesCafes(String locationX, String locationY) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                "location="+locationX + ","+ locationY+"&"+
                "radius=5000&" +
                "type=restaurant&" +
                "key=" + APIKEY;
        JSONObject object = parseJSON(googleRequestURL);

        throw new UnsupportedOperationException();
    }

    public List<Place> getNearbyPlacesHealth(String locationX, String locationY) throws IOException, JSONException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                "location="+locationX + ","+ locationY+"&"+
                "radius=5000&" +
                "type=health&" +
                "key=" + APIKEY;
        JSONObject object = parseJSON(googleRequestURL);

        throw new UnsupportedOperationException();
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

        // voorbeeld voor het uitlezen van JSON request
//        JSONObject res = object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0);
//        System.out.println(res.getString("start_address"));
//        System.out.println(res.getJSONObject("start_location").getString("lat"));
//        System.out.println(res.getJSONObject("start_location").getString("lng"));

    }

    // Vanwege instellingen moet een URL request in een thread gebeuren. Dit zorgt ervoor dat er geen thread nodig is.
    private void ignoreThreadRequirement()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
