package com.example.assignment2.Object;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "ComponentList")
public class Component {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Bike.class, parentColumns = "id", childColumns = "bikeId")
    private int bikeId;

    private int componentLifetime;
    private String dateComponent;

    public int getBikeId() {
        return bikeId;
    }

    public int getComponentLifetime() {
        return componentLifetime;
    }

    public String getDateComponent() {
        return dateComponent;
    }

    public int getId() {
        return id;
    }

    public Component(int id, int bikeId, int componentLifetime, String dateComponent) {
        this.id = id;
        this.bikeId = bikeId;
        this.componentLifetime = componentLifetime;
        this.dateComponent = dateComponent;
    }

    public void setId(int id) {
        this.id = id;
    }
}
