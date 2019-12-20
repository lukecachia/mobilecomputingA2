package com.example.assignment2.object;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ActivityList")
public class Activity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Bike.class, parentColumns = "id", childColumns = "bikeId", onDelete = CASCADE)
    private int bikeId;

    private int distance;


    public int getId() {
        return id;
    }

    public int getBikeId() {
        return bikeId;
    }

    public int getDistance() {
        return distance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activity(int bikeId, int distance) {
        this.bikeId = bikeId;
        this.distance = distance;
    }


}
