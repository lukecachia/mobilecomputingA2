package com.example.assignment2.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.Adapter.ComponentAdapter;
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

    public static final int ADD_COMPONENT_REQUEST  = 1;
    public static final int EDIT_COMPONENT_REQUEST = 2;


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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                appViewModel.deleteComponent(componentAdapter.getComponentAt(viewHolder.getAdapterPosition()));

                Toast.makeText(BikeProfileActivity.this, "Component Deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        componentAdapter.setOnComponentClickListener(new ComponentAdapter.OnComponentClickListener() {
            @Override
            public void onComponentClick(Component component) {
                Intent intent = new Intent(BikeProfileActivity.this, AddEditComponentActivity.class);

                intent.putExtra(AddEditComponentActivity.EXTRA_CID, component.getId());

                intent.putExtra(AddEditComponentActivity.EXTRA_NAME, component.getComponentName());
                intent.putExtra(AddEditComponentActivity.EXTRA_DATE, component.getDateComponent());
                intent.putExtra(AddEditComponentActivity.EXTRA_LIFETIME, component.getComponentLifetime());

                startActivityForResult(intent, EDIT_COMPONENT_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==  ADD_COMPONENT_REQUEST && resultCode == RESULT_OK){
            String cName = data.getStringExtra(AddEditComponentActivity.EXTRA_NAME);
            String cDate = data.getStringExtra(AddEditComponentActivity.EXTRA_DATE);
            String cLifetime = data.getStringExtra(AddEditComponentActivity.EXTRA_LIFETIME);

            Component component = new Component(cName, cDate, cLifetime);
            appViewModel.insertComponent(component);

            Toast.makeText(this, "Component Saved", Toast.LENGTH_LONG).show();

        } else if (requestCode ==  EDIT_COMPONENT_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddEditComponentActivity.EXTRA_CID, -1);

            if(id == -1){
                Toast.makeText(this, "Component cannot be updated", Toast.LENGTH_LONG).show();
                return;
            }

            String cName = data.getStringExtra(AddEditComponentActivity.EXTRA_NAME);
            String cDate = data.getStringExtra(AddEditComponentActivity.EXTRA_DATE);
            String cLifetime = data.getStringExtra(AddEditComponentActivity.EXTRA_LIFETIME);

            Component component = new Component(cName, cDate, cLifetime);
            component.setId(id);
            appViewModel.updateComponent(component);

            Toast.makeText(this, "Component Updated", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Component Not Saved", Toast.LENGTH_LONG).show();

        }
    }

    private void openAddComponentAct() {
        Intent intent = new Intent(this, AddEditComponentActivity.class);
        //startActivity(intent);
        startActivityForResult(intent, ADD_COMPONENT_REQUEST);
    }
}
