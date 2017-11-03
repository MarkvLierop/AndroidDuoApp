package planet.androidduoapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ScheduleOverviewActivity extends AppCompatActivity {

    private FloatingActionButton fabAddPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_overview);

        fabAddPlan = (FloatingActionButton)findViewById(R.id.fabAddPlann);
        fabAddPlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent add = new Intent(ScheduleOverviewActivity.this, AddScheduleActivity.class);
                startActivity(add);
            }
        });
    }
}
