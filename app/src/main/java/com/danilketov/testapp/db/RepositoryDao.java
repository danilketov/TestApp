package com.danilketov.testapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWorkers(ArrayList<Worker> workers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSpecialties(ArrayList<Specialty> specialties);

    @Query("DELETE FROM Worker")
    void deleteWorkers();

    @Query("DELETE FROM Specialty")
    void deleteSpecialties();

    @Query("SELECT * FROM Worker")
    List<Worker> getWorkers();

    @Query("SELECT * FROM Specialty")
    List<Specialty> getSpecialties();
}
