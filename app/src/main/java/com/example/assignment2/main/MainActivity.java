package com.example.assignment2.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.assignment2.R;
import com.example.assignment2.ViewModel.AppViewModel;
import com.example.assignment2.Adapter.BikeAdapter;
import com.example.assignment2.Object.Bike;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final int ADD_BIKE_REQUEST = 1;

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
                bikeAdapter.setBikes(bikes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                appViewModel.deleteBike(bikeAdapter.getBikeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Bike deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        //Shows toast when there are no bikes
        if(bikeAdapter.getItemCount() == 0){
            Toast.makeText(MainActivity.this, "Add a new Bike!", Toast.LENGTH_LONG).show();
        }


    }

    public void openAddBikeActivity(){
        Intent intent =  new Intent(this, AddBikeActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, ADD_BIKE_REQUEST);

    }

    public void openAddActivityActivity(){
        Intent intent = new Intent(this, AddActivityActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_BIKE_REQUEST && resultCode == RESULT_OK){
            String bikeBrand = data.getStringExtra(AddBikeActivity.EXTRA_BIKEBRAND);
            String bikeModel = data.getStringExtra(AddBikeActivity.EXTRA_BIKEMODEL);
            String bikeDOP   = data.getStringExtra(AddBikeActivity.EXTRA_BIKEDOP);

            Bike newBike = new Bike(bikeBrand, bikeModel, bikeDOP);
            appViewModel.insertBike(newBike);

            Toast.makeText(this, "New Bike Saved", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Bike not saved", Toast.LENGTH_SHORT).show();

        }

    }
}
