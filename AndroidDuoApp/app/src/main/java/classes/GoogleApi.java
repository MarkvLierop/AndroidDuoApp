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
    private static String APIKEY = "AIzaSyAekDAiLYoKsFRCFfjFoEb1XoVYnxgIU9g";
    public GoogleApi()
    {

    }

    public Place getPlaceData()
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

    public List<Place> getPlacesRestaurants()
    {
        throw new UnsupportedOperationException();
    }

    public List<Place> getPlacesMuseums()
    {
        throw new UnsupportedOperationException();
    }

    public List<Place> getPlacesCafes()
    {
        throw new UnsupportedOperationException();
    }

    public List<Place> getPlacesAid()
    {
        throw new UnsupportedOperationException();
    }

    public void parseJSON() throws IOException, JSONException
    {
        ignoreThreadRequirement();

        String Url = "https://maps.googleapis.com/maps/api/directions/json?origin=Bakel&destination=Helmond&key=" + APIKEY;

        StringBuilder jsonResults = new StringBuilder();
        URL url = new URL(Url);
        Log.d("tag", Url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        // Load the results into a StringBuilder
        int read;
        char[] buff = new char[1024];
        while ((read = in.read(buff)) != -1) {
            jsonResults.append(buff, 0, read);
        }
        conn.disconnect();

        JSONObject object = new JSONObject(jsonResults.toString());
        JSONObject res = object.getJSONArray("routes").getJSONObject(0).getJSONArray("legs").getJSONObject(0);
        System.out.println(res.getString("start_address"));
        System.out.println(res.getJSONObject("start_location").getString("lat"));
        System.out.println(res.getJSONObject("start_location").getString("lng"));

    }

    private void ignoreThreadRequirement()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
