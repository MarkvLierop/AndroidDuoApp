package planet.androidduoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import classes.GoogleApi;
import classes.Place;
import classes.PublicValues;
import classes.Schedule;
import persistencies.FileSerialization;

public class ScheduleOverviewActivity extends AppCompatActivity {

    final Context context = this;
    final private String FILE_NAME = "Schedules";

    EditText etDate = null;
    Calendar myCalendar = Calendar.getInstance();
    ListView lv = null;

    private FileSerialization fs;

    private FloatingActionButton fabAddPlan;
    private List<Schedule> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_overview);

        lv = (ListView) findViewById(R.id.lvPlannings);

        //Get schedules
        fs = new FileSerialization();
        updateScheduleList();

        fabAddPlan = (FloatingActionButton)findViewById(R.id.fabAddPlann);
        fabAddPlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_add_schedule);

                // set the custom dialog components - text, image and button
                final EditText etName = (EditText) dialog.findViewById(R.id.etScheduleName);
                etDate = (EditText) dialog.findViewById(R.id.etScheduleDate);
                final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }

                };

                etDate.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        new DatePickerDialog(context, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                    }
                });

                Button btn = (Button) dialog.findViewById(R.id.btnAddSchedule);
                // if button is clicked, close the custom dialog
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(etName.getText().equals("") || etDate.getText().equals("Date")) {
                            Toast.makeText(context, "Please fill in everthing!", Toast.LENGTH_LONG).show();
                        } else {
                            DateFormat format = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
                            try {
                                schedules.add(new Schedule(etName.getText().toString(), format.parse(etDate.getText().toString())));
                                fs.addPlanning(context, schedules, FILE_NAME);
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

    private void updateScheduleList() {
        schedules = fs.getPlanningen(context, FILE_NAME);

        ListAdapter adapter = new ListAdapter(context, R.layout.list_item_schedule, schedules);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(context, ScheduleDetailsActivity.class);

                Schedule sched = schedules.get(position);
                i.putExtra("sched", sched);

//                ByteArrayOutputStream bs = new ByteArrayOutputStream();
//                places.get(position).getPlaceImage().compress(Bitmap.CompressFormat.PNG, 50, bs);
//                i.putExtra("image", bs.toByteArray());

                startActivity(i);
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etDate.setText(sdf.format(myCalendar.getTime()));
    }

    public static class ListAdapter extends ArrayAdapter<Schedule> {

        public ListAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ListAdapter(Context context, int resource, List<Schedule> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.list_item_schedule, null);
            }

            Schedule sched = getItem(position);

            if (sched != null) {
                TextView tvname = (TextView) v.findViewById(R.id.tvNameSched);
                TextView tvDate = (TextView) v.findViewById(R.id.tvDate);

                //Format for date string
                Format formatter = new SimpleDateFormat("dd/MM/yy");

                tvname.setText(sched.getName());
                tvDate.setText(formatter.format(sched.getDay()));

            }

            return v;
        }

    }
}
