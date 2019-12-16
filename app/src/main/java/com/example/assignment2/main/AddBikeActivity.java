package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.assignment2.R;
import com.example.assignment2.helper.DBContract;
import com.example.assignment2.helper.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddBikeActivity extends AppCompatActivity {

    private SQLiteDatabase database;

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

        //DB Instance creation
        DBHelper dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();

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
        

//        makeTxtView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                makeTxtView.setText("");
//            }
//        });


//        modelTxtView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                modelTxtView.setText("");
//            }
//        });

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
        if(makeTxtView.getText().toString().trim().length() == 0
                || modelTxtView.getText().toString().trim().length() == 0
                || dopTxtView.getText().toString().trim().length() == 0){
            return;
        }

        String newBikeBrand = makeTxtView.getText().toString();
        String newBikeModel = modelTxtView.getText().toString();
        String newBikeDOP = dopTxtView.getText().toString();

        ContentValues cV = new ContentValues();

        cV.put(DBContract.BikeEntry.COLUMN_BRAND, newBikeBrand);
        cV.put(DBContract.BikeEntry.COLUMN_MODEL, newBikeModel);
        cV.put(DBContract.BikeEntry.COLUMN_DOP, newBikeDOP);

        database.insert(DBContract.BikeEntry.TABLE_NAME, null, cV);

        //Go to Main Activity

    }


    //method which opens the addbikephoto activity
    private void openAddPhotoActivity() {
        Intent intent = new Intent(this, AddBikePhotoActivity.class);
        startActivity(intent);

    }


}
