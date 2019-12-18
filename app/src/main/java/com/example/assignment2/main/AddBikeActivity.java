package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment2.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddBikeActivity extends AppCompatActivity {

    Button photoBtn;
    Button submitBtn;

    private TextView makeTxtView;
    private TextView modelTxtView;
    private TextView dopTxtView;

    //Getting today's date
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String todaysDate = formatter.format(date);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);

        //Photo button
        photoBtn = findViewById(R.id.addBikePhoto);

        //Calls method which opens add photo act
        photoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddPhotoActivity();
            }
        });


        makeTxtView  = findViewById(R.id.bikeBrand);
        modelTxtView = findViewById(R.id.bikeModel);
        dopTxtView   = findViewById(R.id.bikeDOP);
        submitBtn    = findViewById(R.id.bikeAddSubmitBtn);


        //Auto filling the date in the text view
        dopTxtView.setText(todaysDate);
        
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewBike();
            }
        });

    }

    private void addNewBike() {

    }


    //method which opens the addbikephoto activity
    private void openAddPhotoActivity() {
        Intent intent = new Intent(this, AddBikePhotoActivity.class);
        startActivity(intent);

    }


}
