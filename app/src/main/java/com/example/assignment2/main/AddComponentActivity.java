package com.example.assignment2.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddComponentActivity extends AppCompatActivity {
    public static final String EXTRA_NAME =
            "com.example.assignment2.main.EXTRA_NAME";
    public static final String EXTRA_DATE =
            "com.example.assignment2.main.EXTRA_DATE";
    public static final String EXTRA_LIFETIME =
            "com.example.assignment2.main.EXTRA_LIFETIME";


    //Getting today's date
    Date date = Calendar.getInstance().getTime();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String todaysDate = formatter.format(date);

    private TextView componentName;
    private TextView componentDate;
    private TextView componentLifetime;
    private Button   componentAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);

        componentName     = findViewById(R.id.componentName);
        componentDate     = findViewById(R.id.componentDate);
        componentLifetime = findViewById(R.id.componentLifetime);
        componentAddBtn   = findViewById(R.id.componentAddBtn);

        componentDate.setText(todaysDate);

        componentAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComponent();
            }
        });

    }

    private void addComponent(){
        String cName     = componentName.getText().toString();
        String cDate     = componentDate.getText().toString();
        String cLifetime = componentLifetime.getText().toString();

        if(cName.trim().isEmpty() || cDate.trim().isEmpty() || cLifetime.trim().isEmpty()){
            Toast.makeText(this, "Please input data", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, cName);
        data.putExtra(EXTRA_DATE, cDate);
        data.putExtra(EXTRA_LIFETIME, cLifetime);

        setResult(RESULT_OK, data);
        finish();

    }
}
