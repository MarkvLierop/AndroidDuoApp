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
    private final static String APIKEY = "AIzaSyDIk-zk1zWPix0aXszVLkWrU67uI9HtPpc";
//    private final static String APIKEY = "AIzaSyAekDAiLYoKsFRCFfjFoEb1XoVYnxgIU9g";
    //AIzaSyAekDAiLYoKsFRCFfjFoEb1XoVYnxgIU9g
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

        place = setPlaceData(place, googleRequestURL);

        return place;
    }

    private Place setPlaceData(Place place, String googleRequest) throws JSONException, IOException
    {
        JSONObject object = parseJSON(googleRequest);
        JSONArray res = object.getJSONObject("result").getJSONArray("address_components");

        for (int i = 0; i < res.length();i++)
        {
            place.setStreetName(res.getJSONObject(1).getString("long_name"));
            place.setCityName(res.getJSONObject(3).getString("long_name"));
        }

        place.setPlaceImage(getPlacePhoto(object.getJSONObject("result").getJSONArray("photos").getJSONObject(0).getString("photo_reference")));
        place.setPhoneNumber(object.getJSONObject("result").getString("formatted_phone_number"));
        place.setPlaceName(object.getJSONObject("result").getString("name"));

        String lat = object.getJSONObject("result").getJSONObject("geometry").getJSONObject("location").getString("lat");
        String lng = object.getJSONObject("result").getJSONObject("geometry").getJSONObject("location").getString("lng");
        place.setDistanceInM(getDistance(lat, lng));

        //Set rating
        String a = object.getJSONObject("result").getString("rating");
        double b = Double.parseDouble(a); //Integer.parseInt(a);
        place.setStars((int) Math.round(b));

        // werkt niet
        //place.setOpenNow(object.getJSONObject("result").getJSONObject("opening_hours").getString("open_now").equals("true") ? "Yes" : "No");
        // werkt well
        place.setOpenNow("No");

        return place;
    }
    private Bitmap getPlacePhoto(String photoReference) throws IOException
    {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/photo?" +
                "maxwidth=400&" +
                "photoreference=" + photoReference + "&" +
                "key=" + APIKEY;

        URL url = new URL(googleRequestURL);

        return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    }

    private String getDistance(String destinationX, String destinationY) throws IOException, JSONException {
        String googleRequestURL = "https://maps.googleapis.com/maps/api/distancematrix/json?" +
                "units=metric&" +
                "origins="+ PublicValues.myLocationLat + ","+ PublicValues.myLocationLng + "&" +
                "destinations="+ destinationX + "%2C"+ destinationY+"&" +
                "key=" + APIKEY;

        JSONObject object = parseJSON(googleRequestURL);
        String distance = object.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("text");

        return distance;
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
        places = new ArrayList<>();

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
        places = new ArrayList<>();

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
        places = new ArrayList<>();

        String googleRequestURL = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+
                "location="+locationX + ","+ locationY+"&"+
                "radius=5000&" +
                "type=health&" +
                "key=" + APIKEY;

        JSONObject object = parseJSON(googleRequestURL);
        assignNearbyPlaceData(object);

        return places;
    }

    private JSONObject parseJSON(String googleRequestURL) throws IOException, JSONException
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

    public List<Place> getPlaceById(List<String> placeIds) throws IOException, JSONException {
        places = new ArrayList<>();

        int i = 0;
        for (String id : placeIds)
        {
            Place place = new Place();

            String googleRequestURL = "https://maps.googleapis.com/maps/api/place/details/json?" +
                    "placeid="+id+"&" +
                    "key=" + APIKEY;

            JSONObject object = parseJSON(googleRequestURL);

            JSONObject res = object.getJSONArray("result").getJSONObject(i).getJSONObject("geometry").getJSONObject("location");

            place.setLocationX(res.getString("lat"));
            place.setLocationY(res.getString("lng"));
            place.setPlaceID(object.getJSONArray("results").getJSONObject(i).getString("place_id"));

            place = setPlaceData(place, googleRequestURL);
            places.add(place);

            i++;
        }

        return places;
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
