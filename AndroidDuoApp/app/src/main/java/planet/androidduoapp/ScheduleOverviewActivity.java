package planet.androidduoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import classes.GoogleApi;
import classes.PublicValues;
import classes.Schedule;

public class ScheduleOverviewActivity extends AppCompatActivity {

    final Context context = this;

    private FloatingActionButton fabAddPlan;
    private List<Schedule> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_overview);

        schedules = new ArrayList<>();

        fabAddPlan = (FloatingActionButton)findViewById(R.id.fabAddPlann);
        fabAddPlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_add_schedule);

                // set the custom dialog components - text, image and button
                final EditText etName = (EditText) findViewById(R.id.etScheduleName);
                final EditText etDate = (EditText) findViewById(R.id.etScheduleDate);

                Button btn = (Button) dialog.findViewById(R.id.btnAddSchedule);
                // if button is clicked, close the custom dialog
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(etName.getText().equals("") || etDate.getText().equals("")) {
                            Toast.makeText(getApplicationContext(), "Please fill in everthing!", Toast.LENGTH_LONG).show();
                        } else {
                            DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
                            try {
                                schedules.add(new Schedule(etName.getText().toString(), format.parse(etDate.getText().toString())));
                                dialog.dismiss();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

                dialog.show();
            }
        });
    }
}
