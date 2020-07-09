package com.danilketov.testapp.repository;

import com.danilketov.testapp.App;
import com.danilketov.testapp.db.AppDatabase;
import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.network.HttpClient;

import java.io.IOException;
import java.util.ArrayList;

public class DataRepository {

    private HttpClient httpClient = App.getHttpClient();
    private AppDatabase db = App.getAppDatabase();

    public ArrayList<Worker> getWorkers() throws IOException {
        ArrayList<Worker> workers = null;
        try {

            workers = httpClient.getWorkersInfo();
            db.repositoryDao().insertWorkers(workers);
        } catch (IOException e) {
            workers = (ArrayList<Worker>) db.repositoryDao().getWorkers();
        }

        if (workers == null)
            throw new IOException("Can't find repositories entities in db");

        return workers;
    }

    public ArrayList<Specialty> getSpecialties() throws IOException {
        ArrayList<Specialty> specialties = null;
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
