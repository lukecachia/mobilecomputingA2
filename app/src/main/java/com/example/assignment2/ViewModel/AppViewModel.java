package com.example.assignment2.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assignment2.Repo.AppRepository;
import com.example.assignment2.Object.Bike;

import java.util.ArrayList;
import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<Bike>> allBikes;
    private List<String> bikeModel;

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository =  new AppRepository(application);
        allBikes = repository.getAllBikes();
        bikeModel = repository.getBikeModel();
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

    public List<String> getBikeModel(){
        return bikeModel;
    }
}
