package planet.androidduoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.IOException;
import java.net.URL;

public class PlaceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        Intent intent = getIntent();

        ImageView iv = (ImageView)findViewById(R.id.imgplaceDetailsPlaceImage);
        Bitmap b = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("image"),0,getIntent().getByteArrayExtra("image").length);
        iv.setImageBitmap(b);

        EditText name = (EditText)findViewById(R.id.txtplaceName);
        name.setText(intent.getStringExtra("name"));
        name.setEnabled(false);

        EditText address = (EditText)findViewById(R.id.txtplaceDetailsPlaceAdress);
        address.setText(intent.getStringExtra("address"));
        address.setEnabled(false);

        EditText phone = (EditText)findViewById(R.id.txtplaceDetailsPlacePhoneNumber);
        phone.setText(String.format("Phone number: %s", intent.getStringExtra("phone")));
        phone.setEnabled(false);

        EditText open = (EditText)findViewById(R.id.txtplaceDetailsOpenNow);
        open.setText(String.format("Is open now: %s", intent.getStringExtra("open")));
        open.setEnabled(false);

        RatingBar rb = (RatingBar)findViewById(R.id.rbPlaceRating);
        //rb.setNumStars(Integer.parseInt(intent.getStringExtra("stars")));
    }
}
