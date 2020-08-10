package com.danilketov.testapp.repository;

import com.danilketov.testapp.App;
import com.danilketov.testapp.db.AppDatabase;
import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.network.HttpClient;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;

public class DataRepository {

    private HttpClient httpClient = App.getHttpClient();
    private AppDatabase db = App.getAppDatabase();

    @Nullable
    public ArrayList<Worker> getWorkers(ArrayList<Worker> workers) throws IOException {
        db.repositoryDao().insertWorkers(workers);
        workers = (ArrayList<Worker>) db.repositoryDao().getWorkers();

        if (workers == null)
            throw new IOException("Can't find repositories entities in db");

        return workers;
    }

    @Nullable
    public ArrayList<Specialty> getSpecialties() throws IOException {
        ArrayList<Specialty> specialties;
        try {
            specialties = httpClient.getSpecialtyInfo();
            db.repositoryDao().insertSpecialties(specialties);
        } catch (IOException e) {
            specialties = (ArrayList<Specialty>) db.repositoryDao().getSpecialties();
        }

        if (specialties == null)
            throw new IOException("Can't find repositories entities in db");

        return specialties;
    }
}
