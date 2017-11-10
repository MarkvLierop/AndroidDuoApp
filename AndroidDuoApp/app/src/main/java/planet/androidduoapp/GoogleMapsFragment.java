package planet.androidduoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.ByteArrayOutputStream;
import java.util.List;

import classes.Place;


public class GoogleMapsFragment extends Fragment {
    MapView mMapView;
    private GoogleMap googleMap;

    private List<Place> places;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_google_maps, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(51.4555001,5.4805959);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Your location").snippet("You are here!"));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        return rootView;
    }

    public void updatePlaces(List<Place> p) {
        places = p;
        //Clear previous markers
        googleMap.clear();
        LatLng sydney = new LatLng(51.4555001,5.4805959);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Your location").snippet("You are here!"));
        //add all markers
        for(Place pl : places) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(pl.getLocationX()), Double.parseDouble(pl.getLocationY()))).title(pl.getPlaceName()));
        }

        //Set on clickListnere on window
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent i = new Intent(getActivity(), PlaceDetailsActivity.class);

                Place cur = null;
                for(Place plo : places) {
                    if(plo.getPlaceName().equals(marker.getTitle())) {
                        cur = plo;
                    }
                }

                if(cur != null) {
                    i.putExtra("place", cur);

                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    cur.getPlaceImage().compress(Bitmap.CompressFormat.PNG, 50, bs);
                    i.putExtra("image", bs.toByteArray());

                    startActivity(i);
                }

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}