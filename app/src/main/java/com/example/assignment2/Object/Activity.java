package com.example.assignment2.Object;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ActivityList")
public class Activity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Bike.class, parentColumns = "id", childColumns = "bikeId", onDelete = CASCADE)
    private int bikeId;

    private int distance;

    private String dateActivity;


    public int getId() {
        return id;
    }

    public int getBikeId() {
        return bikeId;
    }

    public int getDistance() {
        return distance;
    }

    public String getDateActivity() {
        return dateActivity;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Activity(int bikeId, int distance, String dateActivity) {
        this.bikeId = bikeId;
        this.distance = distance;
        this.dateActivity = dateActivity;
    }


}
