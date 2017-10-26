package planet.androidduoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import classes.GoogleApi;

/**
 * Created by Lorenso on 13-Oct-17.
 */

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";

    private Button btnMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.tab1_map, container, false);

        btnMap = (Button) view.findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ja hier dus kaart!", Toast.LENGTH_SHORT).show();

                GoogleApi ga = new GoogleApi();
                try {
                    ga.parseJSON();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}
