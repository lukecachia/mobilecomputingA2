package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.assignment2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddBikeActivity extends AppCompatActivity {

    private TextView makeTxtView;
    private TextView modelTxtView;
    private TextView dopTxtView;

    //Getting todays date
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String todaysDate = formatter.format(date);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bike);

        makeTxtView  = findViewById(R.id.bikeBrand);
        modelTxtView = findViewById(R.id.bikeModel);
        dopTxtView   = findViewById(R.id.bikeDOP);

        makeTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeTxtView.setText("");
            }
        });

        modelTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelTxtView.setText("");
            }
        });

        //Auto filling the date in the text view
        dopTxtView.setText(todaysDate);



    }



}
