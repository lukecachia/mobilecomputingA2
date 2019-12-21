package com.example.assignment2.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment2.Object.Activity;

import java.util.Date;
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

    @Query("SELECT SUM (Distance) FROM ActivityList WHERE BikeId  LIKE  'BikeList.id'")
    LiveData<Integer> totalDistancePerBike();

    @Query("SELECT SUM (Distance) FROM ActivityList WHERE dateActivity > 'dateComponent'")
    Integer getTotalDistanceOfComponent();




}
