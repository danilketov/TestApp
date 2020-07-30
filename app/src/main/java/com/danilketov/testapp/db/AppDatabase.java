package com.danilketov.testapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;

@Database(entities = {Worker.class, Specialty.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static String DB_NAME = "workers_db";

    public abstract RepositoryDao repositoryDao();
}
