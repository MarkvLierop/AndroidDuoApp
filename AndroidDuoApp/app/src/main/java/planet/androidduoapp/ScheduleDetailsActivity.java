package planet.androidduoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import classes.GoogleApi;
import classes.Place;
import classes.Schedule;

public class ScheduleDetailsActivity extends AppCompatActivity {

    final Context context = this;

    private TextView tvName;
    private TextView tvDate;
    private ListView lvPlaces;

    private Schedule scheduleCur;
    private List<Place> placesSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_details);

        tvName = (TextView)findViewById(R.id.tvScheduleName);
        tvDate = (TextView)findViewById(R.id.tvDateSchedule);
        lvPlaces = (ListView)findViewById(R.id.lvPlacesInSched);

        //Get schedule from previous activity
        scheduleCur = (Schedule)getIntent().getSerializableExtra("sched");

        try {
            inputObject(scheduleCur);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void inputObject(Schedule s) throws IOException, JSONException {
        if(s!=null) {
            //Format for date string
            Format formatter = new SimpleDateFormat("dd/MM/yy");

            tvName.setText(s.getName());
            tvDate.setText(formatter.format(s.getDay()));

            //Get places from google api
            GoogleApi ga = new GoogleApi();
            placesSchedule = ga.getPlaceById(s.getPlaces());

            Tab2Fragment.ListAdapter adapter = new Tab2Fragment.ListAdapter(context, R.layout.list_item_overview, placesSchedule);
            lvPlaces.setAdapter(adapter);
        }
    }
}
