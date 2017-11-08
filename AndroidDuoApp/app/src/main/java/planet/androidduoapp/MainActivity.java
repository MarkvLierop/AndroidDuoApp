package planet.androidduoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import  android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.GoogleApi;
import classes.Place;
import classes.PublicValues;

import static planet.androidduoapp.R.id.container;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    final Context context = this;

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private Button btnApplyFilter;
    private FloatingActionButton fab;

    private List<Place> places;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        btnApplyFilter = (Button)findViewById(R.id.btnApplyFilter);
        btnApplyFilter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_filter);
                //dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                Spinner spinner = (Spinner) dialog.findViewById(R.id.spFacilityType);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.facility_types, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                Button dialogButton = (Button) dialog.findViewById(R.id.btnApply);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
//                        GoogleApi ga = new GoogleApi();
//                        try {
//                            places = ga.getNearbyPlacesRestaurants("51.4555001","5.4805959");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                });

                dialog.show();
            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fabPlan);
        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent plan = new Intent(MainActivity.this, ScheduleOverviewActivity.class);
                startActivity(plan);
            }
        });


//        //Register for context menu.
//        registerForContextMenu(btnApplyFilter);
    }

    public void startFilterActivity() {

    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        if(v.getId() == R.id.btnApplyFilter) {
//            this.getMenuInflater().inflate(R.menu.menu_filter, menu);
//        }
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new GoogleMapsFragment(), "Map");
        adapter.addFragment(new Tab2Fragment(), "Overview");
        viewPager.setAdapter(adapter);
    }

    public List<Place> getPlaces() {
        if (places == null)
        {
            GoogleApi ga = new GoogleApi();
            try {
                places = ga.getNearbyPlacesRestaurants(PublicValues.myLocationLat, PublicValues.myLocationLng);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return places;
    }


    public class SectionsPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }

}
