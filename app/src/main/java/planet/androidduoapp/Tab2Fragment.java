package planet.androidduoapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import classes.Place;

/**
 * Created by Lorenso on 13-Oct-17.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";
    private ArrayList<Place> places;

    private Button btnOverview;
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab2_overview, container, false);

        btnOverview = (Button) view.findViewById(R.id.btnSort);
        btnOverview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Soorteer optiekes!", Toast.LENGTH_SHORT).show();
            }
        });

        places = loadPlaces();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        LinearLayout ll = (LinearLayout) view.findViewById(R.id.llToLoad);

//        for(Button btn: loadPlaces()) {
//            ll.addView(btn);
//        }



        ListView lv = (ListView) view.findViewById(R.id.lvPlaces);

        Adapter adapter = new Adapter();
        lv.setAdapter(adapter);

    }

    private ArrayList<Place> loadPlaces() {
//        places = new ArrayList<Place>();
//        List<Button> buttons = new ArrayList<>();
//        Place p = new Place();
//        p.setPlaceName("Test gooiende");
//        places.add(p);
//
//        for (Place place : places) {
//            Button toAdd = new Button(this.getContext());
//            toAdd.setText(place.getPlaceName());
//
//            buttons.add(toAdd);
//
//        }
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://tctechcrunch2011.files.wordpress.com/2014/07/restaurant.jpg").getContent());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Timestamp current = new Timestamp(12, 12, 12, 12,12,12,12);

        ArrayList<Place> placesNew = new ArrayList<Place>();

        Place p = new Place();

        p.setPlaceImage(bitmap);
        p.setPlaceName("Lorenso restaurant");
        p.setOpenTime(current);
        p.setDistanceInM(600);

        placesNew.add(p);

        return placesNew;
    }

    private class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return places.size();
        }

        @Override
        public Object getItem(int position) {
            return places.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getActivity().getLayoutInflater().inflate(R.layout.list_item_overview, null);

            ImageView ivPic = (ImageView) view.findViewById(R.id.ivPicPlace);
            TextView tvname = (TextView) view.findViewById(R.id.tvNamePlace);
            RatingBar rbRating = (RatingBar) view.findViewById(R.id.rbRatingPlace);
            TextView tvOpeningTime = (TextView) view.findViewById(R.id.tvOpeningTimePlace);
            TextView tvDistance = (TextView) view.findViewById(R.id.tvDistancePlace);

            ivPic.setImageBitmap(places.get(position).getPlaceImage());
            tvname.setText(places.get(position).getPlaceName());
            rbRating.setRating(places.get(position).getStars());
            tvOpeningTime.setText(places.get(position).getOpenTime().toString());
            tvDistance.setText(places.get(position).getDistanceInM());

            return view;
        }
    }
}


