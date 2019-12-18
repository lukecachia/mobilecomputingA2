package com.example.assignment2.object;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BikeList")
public class Bike {


    //private int bikeImage;
    @PrimaryKey(autoGenerate = true)
    private int bikeId;

    private String bikeBrand;
    private String bikeModel;
    private String bikeDOP;

    public int getBikeId() {
        return bikeId;
    }

    public String getBikeBrand() {
        return bikeBrand;
    }

    public String getBikeModel() {
        return bikeModel;
    }

    public String getBikeDOP() {
        return bikeDOP;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public Bike(String bikeBrand, String bikeModel, String bikeDOP) {
        this.bikeBrand = bikeBrand;
        this.bikeModel = bikeModel;
        this.bikeDOP = bikeDOP;
    }
}
