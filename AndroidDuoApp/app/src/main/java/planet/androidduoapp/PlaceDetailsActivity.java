package planet.androidduoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;

import classes.Place;

public class PlaceDetailsActivity extends AppCompatActivity {

    ImageView image;
    TextView name;
    TextView adres;
    TextView phone;
    TextView isOpen;
    RatingBar rating;
    Button addToSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        image = (ImageView)findViewById(R.id.imgplaceDetailsPlaceImage);;
        name = (TextView)findViewById(R.id.tvName);
        adres = (TextView)findViewById(R.id.tvAdress);
        phone = (TextView)findViewById(R.id.tvPhone);
        isOpen = (TextView)findViewById(R.id.tvIsOpen);
        rating = (RatingBar)findViewById(R.id.rbPlaceRating);

        addToSchedule = (Button) findViewById(R.id.btnAddToPlanning);

        Place p = (Place) getIntent().getSerializableExtra("place");
        p.setPlaceImage(BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length));

        inputObject(p);


//        ImageView iv = (ImageView)findViewById(R.id.imgplaceDetailsPlaceImage);
//        Bitmap b = BitmapFactory.decodeByteArray(
//                getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length);
//        iv.setImageBitmap(b);
//
//        EditText name = (EditText)findViewById(R.id.txtplaceName);
//        name.setText(intent.getStringExtra("name"));
//        name.setEnabled(false);
//
//        EditText address = (EditText)findViewById(R.id.txtplaceDetailsPlaceAdress);
//        address.setText(intent.getStringExtra("address"));
//        address.setEnabled(false);
//
//        EditText phone = (EditText)findViewById(R.id.txtplaceDetailsPlacePhoneNumber);
//        phone.setText(String.format("Phone number: %s", intent.getStringExtra("phone")));
//        phone.setEnabled(false);
//
//        EditText open = (EditText)findViewById(R.id.txtplaceDetailsOpenNow);
//        open.setText(String.format("Is open now: %s", intent.getStringExtra("open")));
//        open.setEnabled(false);
//
//        RatingBar rb = (RatingBar)findViewById(R.id.rbPlaceRating);
//        //rb.setRating(Integer.parseInt(intent.getStringExtra("stars")));
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
