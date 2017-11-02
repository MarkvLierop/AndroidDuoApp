package planet.androidduoapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

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
                // custom dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_sort);
                //dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                Spinner spinner = (Spinner) dialog.findViewById(R.id.spSort);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.sort_options, android.R.layout.simple_spinner_item);
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
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        lv = (ListView) view.findViewById(R.id.lvPlaces);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        places = loadPlaces();

        ListAdapter adapter = new ListAdapter(this.getContext(), R.layout.list_item_overview, places);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }


    private ArrayList<Place> loadPlaces() {
//        Bitmap bitmap = null;
//        try {
//            bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://tctechcrunch2011.files.wordpress.com/2014/07/restaurant.jpg").getContent());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Timestamp now = new Timestamp(new java.util.Date().getTime());

        ArrayList<Place> placesNew = new ArrayList<Place>();

//        Place p = new Place();
//
//        p.setPlaceImage(bitmap);
//        p.setPlaceName("Lorenso restaurant");
//        p.setOpenTime(now);
//        p.setDistanceInM(600);
//
//        placesNew.add(p);

        return placesNew;
    }


    public class ListAdapter extends ArrayAdapter<Place> {

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, ArrayList<Place> items) {
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
                tvOpeningTime.setText(p.getOpenTime().toString());
                tvDistance.setText(p.getDistanceInM());
            }

            return v;
        }

    }
    }


