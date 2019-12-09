package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.assignment2.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;


public class MainActivity extends AppCompatActivity {

    Boolean isOpen = false;

    private ExtendedFloatingActionButton fabMain;
    private ExtendedFloatingActionButton fabAddBike;
    private ExtendedFloatingActionButton fabAddActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabMain        = findViewById(R.id.mainFab);
        fabAddBike     = findViewById(R.id.mainAddBike);
        fabAddActivity = findViewById(R.id.mainAddActivity);

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

        fabAddBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBikeActivity();
            }
        });


    }

    public void openAddBikeActivity(){
        Intent intent =  new Intent(this, AddBikeActivity.class);
        startActivity(intent);
    }
}
