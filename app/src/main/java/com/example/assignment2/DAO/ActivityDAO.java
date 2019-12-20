package com.example.assignment2.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment2.object.Activity;
import com.example.assignment2.object.Bike;

import java.util.List;

@Dao
public interface ActivityDAO {

    @Insert
    void insertActivity(Activity activity);

    @Update
    void updateActivity(Activity activity);

    @Delete
    void deleteActivity(Activity activity);

    @Query("SELECT * FROM ActivityList")
    LiveData<List<Activity>> selectAllActivities();

    @Query("SELECT Distance FROM ActivityList WHERE BikeId  LIKE  'BikeList.id'")
    LiveData<List<Integer>> getAllActivityPerBike();

    @Query("SELECT SUM (Distance) FROM ActivityList WHERE BikeId  LIKE  'BikeList.id'")
    int totalDistanceByABike();



}
