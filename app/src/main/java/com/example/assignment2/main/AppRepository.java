package com.example.assignment2.main;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.assignment2.DAO.BikeDAO;
import com.example.assignment2.object.Bike;

import java.util.List;

public class AppRepository {
    private BikeDAO bikeDAO;
    private LiveData<List<Bike>> allBikes;

    public AppRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);

        bikeDAO = database.bikeDAO();
        allBikes = bikeDAO.selectAllBikes();

    }

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



}
