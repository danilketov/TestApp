package com.danilketov.testapp.network;

import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;

class JsonParser {

    private static final Gson GSON = new Gson();

    @NotNull
    ArrayList<Worker> getWorkersInfo(@NotNull String jsonString) {

        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonArray responseArray = jsonObject.getAsJsonArray("response");
        Type responseCollectionType = new TypeToken<ArrayList<Worker>>() {}.getType();
        ArrayList<Worker> result = GSON.fromJson(responseArray, responseCollectionType);

        return result;
    }

    @NotNull
    ArrayList<Specialty> getSpecialtyInfo(@NotNull String jsonString) {

        ArrayList<Worker> workersInfo = getWorkersInfo(jsonString);

        ArrayList<Specialty> specialties = new ArrayList<Specialty>();
        for (Worker workerInfo : workersInfo) {
            specialties.addAll(workerInfo.getSpecialty());
        }

        return specialties;
    }
}
