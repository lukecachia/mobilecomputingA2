package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.assignment2.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class BikeProfileActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.assignment2.main.EXTRA_ID";
    public static final String EXTRA_BBRAND =
            "com.example.assignment2.main.EXTRA_BBRAND";
    public static final String EXTRA_BMODEL =
            "com.example.assignment2.main.EXTRA_BMODEL";


    TextView bikeTitle;
    ExtendedFloatingActionButton mainFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_profile);

        bikeTitle = findViewById(R.id.bikeTitle);
        mainFab   = findViewById(R.id.addComponentFab);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            bikeTitle.setText(intent.getStringExtra(EXTRA_BBRAND) + " " + intent.getStringExtra(EXTRA_BMODEL));

        }


    }
}
