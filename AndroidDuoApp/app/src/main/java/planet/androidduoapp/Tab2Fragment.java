package planet.androidduoapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    private LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab2_overview, container, false);

//        btnOverview = (Button) view.findViewById(R.id.btnOverview);
//        btnOverview.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "Ja hier dus overzicht!", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout ll = (LinearLayout) view.findViewById(R.id.llToLoad);

        for(Button btn: loadPlaces()) {
            ll.addView(btn);
        }
    }

    private List<Button> loadPlaces() {
        places = new ArrayList<Place>();
        List<Button> buttons = new ArrayList<>();
        Place p = new Place();
        p.setPlaceName("Test gooiende");
        places.add(p);

        for (Place place : places) {
            Button toAdd = new Button(this.getContext());
            toAdd.setText(place.getPlaceName());

            buttons.add(toAdd);

        }
        return buttons;
    }
}


