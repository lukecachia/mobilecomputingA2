package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.Adapter.ComponentAdapter;
import com.example.assignment2.Object.Bike;
import com.example.assignment2.Object.Component;
import com.example.assignment2.R;
import com.example.assignment2.ViewModel.AppViewModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;

public class BikeProfileActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.assignment2.main.EXTRA_ID";
    public static final String EXTRA_BBRAND =
            "com.example.assignment2.main.EXTRA_BBRAND";
    public static final String EXTRA_BMODEL =
            "com.example.assignment2.main.EXTRA_BMODEL";

    public static final int ADD_COMPONENT_REQUEST = 1;


    TextView bikeTitle;
    ExtendedFloatingActionButton mainFab;

    private AppViewModel appViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_profile);

        bikeTitle = findViewById(R.id.bikeTitle);
        mainFab   = findViewById(R.id.addComponentFab);

        Intent intent = getIntent();

        //Check to determine if user has click on a particular item
        //and to set the text from the item click
        if(intent.hasExtra(EXTRA_ID)){
            bikeTitle.setText(intent.getStringExtra(EXTRA_BBRAND) + " " + intent.getStringExtra(EXTRA_BMODEL));

        }

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddComponentAct();
            }
        });


        //
        RecyclerView recyclerView = findViewById(R.id.componentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //
        final ComponentAdapter componentAdapter = new ComponentAdapter();
        recyclerView.setAdapter(componentAdapter);

        appViewModel = ViewModelProviders.of(this).get(AppViewModel.class);
        appViewModel.getAllComponents().observe(this, new Observer<List<Component>>() {
            @Override
            public void onChanged(List<Component> components) {
                //Update RecyclerView
                componentAdapter.setComponents(components);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==  ADD_COMPONENT_REQUEST && resultCode == RESULT_OK){
            String cName = data.getStringExtra(AddComponentActivity.EXTRA_NAME);
            String cDate = data.getStringExtra(AddComponentActivity.EXTRA_DATE);
            String cLifetime = data.getStringExtra(AddComponentActivity.EXTRA_LIFETIME);

            Component component = new Component(cName, cDate, cLifetime);
            appViewModel.insertComponent(component);

            Toast.makeText(this, "Component Saved", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Component Not Saved", Toast.LENGTH_LONG).show();

        }
    }

    private void openAddComponentAct() {
        Intent intent = new Intent(this, AddComponentActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, ADD_COMPONENT_REQUEST);
    }
}
