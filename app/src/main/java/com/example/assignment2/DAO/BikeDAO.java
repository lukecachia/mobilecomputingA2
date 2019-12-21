package com.example.assignment2.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment2.Object.Bike;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BikeDAO {

    @Insert
    void insertBike(Bike bike);

    @Update
    void updateBike(Bike bike);

    @Delete
    void deleteBike(Bike bike);

    @Query("SELECT * FROM BikeList")
    LiveData<List<Bike>> selectAllBikes();

    @Query("SELECT bikeModel FROM BikeList")
    List<String> getBikeModel();

}
