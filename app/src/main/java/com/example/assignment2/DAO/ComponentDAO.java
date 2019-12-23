package com.example.assignment2.DAO;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignment2.Object.Component;

import java.util.List;

@Dao
public interface ComponentDAO {

    @Insert
    void insert(Component component);

    @Update
    void update(Component component);

    @Delete
    void delete(Component component);

    @Query("SELECT * FROM ComponentList")
    LiveData<List<Component>> getAllComponents();
}