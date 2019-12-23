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



    private String componentName;
    private String componentLifetime;
    private String dateComponent;

    public int getBikeId() {
        return bikeId;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getComponentLifetime() {
        return componentLifetime;
    }

    public String getDateComponent() {
        return dateComponent;
    }

    public int getId() {
        return id;
    }

    public Component(String componentName, String componentLifetime, String dateComponent) {
        this.componentName = componentName;
        this.componentLifetime = componentLifetime;
        this.dateComponent = dateComponent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }
}
