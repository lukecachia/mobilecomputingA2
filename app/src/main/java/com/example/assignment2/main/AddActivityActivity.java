package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignment2.Object.BikeModel;
import com.example.assignment2.R;

import java.util.ArrayList;
import java.util.List;

public class AddActivityActivity extends AppCompatActivity{

    Spinner bikeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        bikeSpinner = findViewById(R.id.selectBikeSpn);

        List<BikeModel> spinnerBikeList = new ArrayList<>();
        BikeModel model1 = new BikeModel("Canyon ", "Aeroad");
        spinnerBikeList.add(model1);

        BikeModel model2 = new BikeModel("Cervelo ", "R2");
        spinnerBikeList.add(model2);


        ArrayAdapter<BikeModel> adapter = new ArrayAdapter<BikeModel>(this,
                android.R.layout.simple_spinner_item, spinnerBikeList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bikeSpinner.setAdapter(adapter);


        bikeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BikeModel bikeModel = (BikeModel) parent.getSelectedItem();
                displayBikeData(bikeModel);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void getSelectedUser(View v){
        BikeModel bikeModel = (BikeModel) bikeSpinner.getSelectedItem();
        displayBikeData(bikeModel);
    }

    private void displayBikeData(BikeModel bikeModel){
        String bBrand = bikeModel.getBikeBrand();
        String bModel = bikeModel.getBikeModel();

        String bData = bBrand + " " + bModel;

        Toast.makeText(this, bData, Toast.LENGTH_LONG).show();
    }


}
