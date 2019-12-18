package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.assignment2.R;
import com.example.assignment2.object.Bike;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    Boolean isOpen = false;

    private ExtendedFloatingActionButton fabMain;
    private ExtendedFloatingActionButton fabAddBike;
    private ExtendedFloatingActionButton fabAddActivity;

    private AppViewModel appViewModel;

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

        //Method to enable the action btn to open the add bike screen
        fabAddBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddBikeActivity();
            }
        });

        //Method to enable the action btn to open the add activity screen
        fabAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddActivityActivity();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BikeAdapter bikeAdapter = new BikeAdapter();
        recyclerView.setAdapter(bikeAdapter);

        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllBikes().observe(this, new Observer<List<Bike>>() {
            @Override
            public void onChanged(List<Bike> bikes) {
                //Update Recycleview
                //Toast.makeText(MainActivity.this,  "onChange", Toast.LENGTH_LONG).show();

                bikeAdapter.setBikes(bikes);
            }
        });
    }

    public void openAddBikeActivity(){
        Intent intent =  new Intent(this, AddBikeActivity.class);
        startActivity(intent);

    }

    public void openAddActivityActivity(){
        Intent intent = new Intent(this, AddActivityActivity.class);
        startActivity(intent);
    }
}
