package com.example.assignment2.Repo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.assignment2.DAO.ActivityDAO;
import com.example.assignment2.DAO.BikeDAO;
import com.example.assignment2.DAO.ComponentDAO;
import com.example.assignment2.Object.Activity;
import com.example.assignment2.Object.Component;
import com.example.assignment2.db.AppDatabase;
import com.example.assignment2.Object.Bike;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppRepository {

    //BikeRepo
    private BikeDAO bikeDAO;
    private LiveData<List<Bike>> allBikes;
    private List<String> bikeModel;

    //ActivityRepo
    private ActivityDAO activityDAO;
    private LiveData<List<Activity>> allActivities;
    private LiveData<Integer> allActivitiesByBike;
    private int allActivitiesByBikePerComponent;

    //ComponentRepo
    private ComponentDAO componentDAO;
    private LiveData<List<Component>> allComponents;


    public AppRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);

        //BikeDAO
        bikeDAO = database.bikeDAO();
        allBikes = bikeDAO.selectAllBikes();

        //ActivityDAO
        activityDAO = database.activityDAO();

        allActivities = activityDAO.selectAllActivities();
        allActivitiesByBike = activityDAO.totalDistancePerBike();
        //allActivitiesByBikePerComponent = activityDAO.getTotalDistanceOfComponent();

        //ComponentDAO
        componentDAO = database.componentDAO();
        allComponents = componentDAO.getAllComponents();


    }


    //Bike
    public void insertBike(Bike bike){
        new InsertBikeAsyncTask(bikeDAO).execute(bike);
    }

    public void updateBike(Bike bike){
        new UpdateBikeAsyncTask(bikeDAO).execute(bike);
    }

    public void deleteBike(Bike bike){
        new DeleteBikeAsyncTask(bikeDAO).execute(bike);
    }

    public LiveData<List<Bike>> getAllBikes() {
        return allBikes;
    }

    public List<String> getBikeModel(){
        return bikeModel;
    }

    private static class InsertBikeAsyncTask extends AsyncTask<Bike, Void, Void> {

        private BikeDAO bikeDAO;

        private InsertBikeAsyncTask(BikeDAO bikeDAO){
            this.bikeDAO = bikeDAO;

        }

        @Override
        protected Void doInBackground(Bike... bikes) {
            bikeDAO.insertBike(bikes[0]);
            return null;
        }
    }

    private static class UpdateBikeAsyncTask extends AsyncTask<Bike, Void, Void> {

        private BikeDAO bikeDAO;

        private UpdateBikeAsyncTask(BikeDAO bikeDAO){
            this.bikeDAO = bikeDAO;

        }

        @Override
        protected Void doInBackground(Bike... bikes) {
            bikeDAO.updateBike(bikes[0]);
            return null;
        }
    }

    private static class DeleteBikeAsyncTask extends AsyncTask<Bike, Void, Void> {

        private BikeDAO bikeDAO;

        private DeleteBikeAsyncTask(BikeDAO bikeDAO){
            this.bikeDAO = bikeDAO;

        }

        @Override
        protected Void doInBackground(Bike... bikes) {
            bikeDAO.deleteBike(bikes[0]);
            return null;
        }
    }


    //Activity


    //Component
    public void insertComponent(Component component){
        new InsertComponentAsyncTask(componentDAO).execute(component);
    }

    public void updateComponent(Component component){
        new UpdateComponentAsyncTask(componentDAO).execute(component);
    }


    public void deleteComponent(Component component){
        new DeleteComponentAsyncTask(componentDAO).execute(component);
    }

    public LiveData<List<Component>> getAllComponents() {
        return allComponents;
    }

    private static class InsertComponentAsyncTask  extends AsyncTask<Component, Void, Void> {
        private ComponentDAO componentDAO;

        private InsertComponentAsyncTask(ComponentDAO componentDAO){
            this.componentDAO = componentDAO;
        }

        @Override
        protected Void doInBackground(Component... components) {
            componentDAO.insert(components[0]);
            return null;
        }

    }

    private static class UpdateComponentAsyncTask extends AsyncTask<Component, Void, Void> {
        private ComponentDAO componentDAO;

        private UpdateComponentAsyncTask(ComponentDAO componentDAO){
            this.componentDAO = componentDAO;
        }

        @Override
        protected Void doInBackground(Component... components) {
            componentDAO.update(components[0]);
            return null;
        }
    }

    private static class DeleteComponentAsyncTask extends AsyncTask<Component, Void, Void> {
        private ComponentDAO componentDAO;

        private DeleteComponentAsyncTask(ComponentDAO componentDAO){
            this.componentDAO = componentDAO;

        }

        @Override
        protected Void doInBackground(Component... components) {
            componentDAO.delete(components[0]);
            return null;
        }
    }





}
