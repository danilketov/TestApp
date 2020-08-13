package com.danilketov.testapp;

import android.app.Application;

import androidx.room.Room;

import com.danilketov.testapp.db.AppDatabase;
import com.danilketov.testapp.repository.DataRepository;

public class App extends Application {

    private static AppDatabase appDatabase;
    private static DataRepository dataRepository;
    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static DataRepository getDataRepository() {
        if (dataRepository == null) {
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    public static AppDatabase getAppDatabase() {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(INSTANCE, AppDatabase.class, AppDatabase.DB_NAME).build();
        }
        return appDatabase;
    }
}
