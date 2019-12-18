package com.example.assignment2.main;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.assignment2.DAO.BikeDAO;
import com.example.assignment2.object.Bike;


@Database(entities = {Bike.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase instance;

    public abstract BikeDAO bikeDAO();


    //singleton method
    public static synchronized AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private BikeDAO bikeDAO;

        private PopulateDbAsyncTask(AppDatabase db){
            bikeDAO = db.bikeDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bikeDAO.insertBike(new Bike("SampleBrand", "SampleModel", "18/12/2019"));

            return null;
        }
    }
}
