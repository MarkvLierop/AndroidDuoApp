package planet.androidduoapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import classes.GoogleApi;
import classes.Place;


/**
 * Created by Lorenso on 13-Oct-17.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";
    private List<Place> places;

    private Button btnOverview;
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab2_overview, container, false);

        btnOverview = (Button) view.findViewById(R.id.btnSort);
        btnOverview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_sort);
                //dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                final Spinner spinner = (Spinner) dialog.findViewById(R.id.spSort);
                final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.sort_options, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

//                TextView text = (TextView) dialog.findViewById(R.id.text);
//                text.setText("Android custom dialog example!");
//                ImageView image = (ImageView) dialog.findViewById(R.id.image);
//                image.setImageResource(R.drawable.ic_launcher);

                Button dialogButton = (Button) dialog.findViewById(R.id.btnSort);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (spinner.getSelectedItem().toString())
                        {
                            case "Alpabet asc.":
                                Collections.sort(places, new Comparator<Place>() {
                                    @Override
                                    public int compare(Place o1, Place o2) {
                                        return o1.getPlaceName().compareTo(o2.getPlaceName());
                                    }
                                });
                                break;
                            case "Alpabet dec.":
                                Collections.reverse(places);
                                break;
                            case "Distance asc.":
                                Collections.sort(places, new Comparator<Place>() {
                                    @Override
                                    public int compare(Place o1, Place o2) {
                                        return o1.getDistanceInM().compareTo(o2.getDistanceInM());
                                    }
                                });
                                break;
                            case "Distance dec.":
                                Collections.reverse(places);
                                break;
                        }
                        updatePlaces(places);
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        lv = (ListView) view.findViewById(R.id.lvPlaces);

        return view;
    }

    public void updatePlaces(List<Place> p) {
        this.places = p;

        ListAdapter adapter = new ListAdapter(this.getContext(), R.layout.list_item_overview, places);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), PlaceDetailsActivity.class);

                Place p = places.get(position);
                i.putExtra("place", p);

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                places.get(position).getPlaceImage().compress(Bitmap.CompressFormat.PNG, 50, bs);
                i.putExtra("image", bs.toByteArray());

                startActivity(i);
            }
        });

    }


    public static class ListAdapter extends ArrayAdapter<Place> {

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, List<Place> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.list_item_overview, null);
            }

            Place p = getItem(position);

            if (p != null) {
                ImageView ivPic = (ImageView) v.findViewById(R.id.ivPicPlace);
                TextView tvname = (TextView) v.findViewById(R.id.tvNamePlace);
                RatingBar rbRating = (RatingBar) v.findViewById(R.id.rbRatingPlace);
                TextView tvOpeningTime = (TextView) v.findViewById(R.id.tvOpeningTimePlace);
                TextView tvDistance = (TextView) v.findViewById(R.id.tvDistancePlace);

                ivPic.setImageBitmap(p.getPlaceImage());
                tvname.setText(p.getPlaceName());
                rbRating.setRating(p.getStars());
                tvOpeningTime.setText(String.format("Is open now: %s", p.isOpenNow()));
                tvDistance.setText(String.format("%s van je vandaan.", String.valueOf(p.getDistanceInM())));
            }

            return v;
        }

    }
}


