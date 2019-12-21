package com.example.assignment2.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.assignment2.DAO.ActivityDAO;
import com.example.assignment2.DAO.BikeDAO;
import com.example.assignment2.Object.Activity;
import com.example.assignment2.Object.Bike;
import com.example.assignment2.Object.Component;


@Database(entities = {Bike.class, Activity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase instance;

    public abstract BikeDAO bikeDAO();

    public abstract ActivityDAO activityDAO();


    //public abstract ComponentDAO componentDAO();



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
        private ActivityDAO activityDAO;

        private PopulateDbAsyncTask(AppDatabase db){
            bikeDAO = db.bikeDAO();
            activityDAO = db.activityDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
