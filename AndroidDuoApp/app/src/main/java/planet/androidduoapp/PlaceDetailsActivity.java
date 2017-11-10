package planet.androidduoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import classes.GoogleApi;
import classes.Place;
import classes.PublicValues;
import classes.Schedule;
import persistencies.FileSerialization;

public class PlaceDetailsActivity extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView adres;
    TextView phone;
    TextView isOpen;
    RatingBar rating;
    Button addToSchedule;

    final private String FILE_NAME = "Schedules";

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        image = (ImageView)findViewById(R.id.imgplaceDetailsPlaceImage);;
        name = (TextView)findViewById(R.id.tvScheduleName);
        adres = (TextView)findViewById(R.id.tvAdress);
        phone = (TextView)findViewById(R.id.tvPhone);
        isOpen = (TextView)findViewById(R.id.tvIsOpen);
        rating = (RatingBar)findViewById(R.id.rbPlaceRating);

        final Place p = (Place) getIntent().getSerializableExtra("place");
        p.setPlaceImage(BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length));

        inputObject(p);

        addToSchedule = (Button) findViewById(R.id.btnAddToPlanning);
        addToSchedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_add_place_to_sched);

                //Get schedules
                final FileSerialization fs = new FileSerialization();
                final List<Schedule> schedules = fs.getPlanningen(context, FILE_NAME);

                // set the custom dialog components - text, image and button
                final ListView lvSchedules = (ListView) dialog.findViewById(R.id.lvChooseSched);

                ScheduleOverviewActivity.ListAdapter adapter = new ScheduleOverviewActivity.ListAdapter(context, R.layout.list_item_schedule, schedules);
                lvSchedules.setAdapter(adapter);
                lvSchedules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Schedule toChange = (Schedule) lvSchedules.getItemAtPosition(position);
                        //Voeg toe aan die place
                        //TODO verander dit nog naar een juiste controle of maak naam uniek
                        int indexToChange = -1;
                        for(Schedule s : schedules) {
                            if(s.getName().equals(toChange.getName())) {
                                indexToChange = schedules.indexOf(s);
                            }
                        }

                        if(indexToChange!=-1) {
                            schedules.get(indexToChange).getPlaces().add(p.getPlaceID());
                            fs.addPlanning(context, schedules, FILE_NAME);
                            Toast.makeText(context, "Place has been added to your selected schedule", Toast.LENGTH_SHORT).show();
                        }


                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });



    }

    public void inputObject(Place p) {
        if(p!=null) {
            image.setImageBitmap(p.getPlaceImage());
            name.setText(p.getPlaceName());
            adres.setText("Adress: " + p.getStreetName() + ", " + p.getCityName());
            phone.setText("Phone number: " + p.getPhoneNumber());
            isOpen.setText("Is open now: " + p.isOpenNow());
            rating.setRating(p.getStars());
        }
    }
}
