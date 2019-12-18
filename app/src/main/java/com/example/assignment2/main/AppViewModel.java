package com.example.assignment2.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignment2.object.Bike;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<Bike>> allBikes;

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository =  new AppRepository(application);
        allBikes = repository.getAllBikes();
    }


    public void insertBike(Bike bike){
        repository.insertBike(bike);
    }

    public void updateBike(Bike bike){
        repository.updateBike(bike);
    }

    public void deleteBike(Bike bike){
        repository.deleteBike(bike);
    }

    public LiveData<List<Bike>> getAllBikes(){
        return allBikes;
    }
}
