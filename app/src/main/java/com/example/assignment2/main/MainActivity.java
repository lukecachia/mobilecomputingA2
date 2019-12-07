package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import android.view.View;


import com.example.assignment2.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class MainActivity extends AppCompatActivity {

    Boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ExtendedFloatingActionButton fabMain        = findViewById(R.id.mainFab);
        final ExtendedFloatingActionButton fabAddBike     = findViewById(R.id.mainAddBike);
        final ExtendedFloatingActionButton fabAddActivity = findViewById(R.id.mainAddActivity);

        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOpen == true){
                    fabAddBike.setVisibility(View.INVISIBLE);
                    fabAddActivity.setVisibility(View.INVISIBLE);
                    isOpen = false;
                } else {
                    fabAddBike.setVisibility(View.VISIBLE);
                    fabAddActivity.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
            }
        });


    }
}
