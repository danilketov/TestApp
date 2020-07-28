package com.danilketov.testapp.utils;

import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Filter {

    // Фильтр отображения работников по выбранным специальностям
    @NotNull
    public static ArrayList<Worker> getFilteredWorkers(@Nullable ArrayList<Worker> result,
                                                       @Nullable String nameSpecialty) {
        Iterator<Worker> i = result.iterator();
        while (i.hasNext()) {
            Worker worker = i.next();
            boolean suitable = false;
            for (Specialty oneSpecialty : worker.getSpecialty()) {
                if (oneSpecialty.getName().equals(nameSpecialty)) {
                    suitable = true;
                }
            }
            if (!suitable)
                i.remove();
        }
        return result;
    }

    // Отображение специальности в описании работника
    @NotNull
    public static String getSpecialtyText(@Nullable String specialtyJSON) {
        Type listType = new TypeToken<ArrayList<Specialty>>() {}.getType();
        List<Specialty> specialties = new Gson().fromJson(specialtyJSON, listType);
        String specialtyText = "-";
        if (specialties != null) {
            StringBuilder specialtyTextBuilder = new StringBuilder();
            for (Specialty specialty : specialties) {
                specialtyTextBuilder.append(specialty.getName()).append(", ");
            }
            specialtyText = specialtyTextBuilder.toString();
        }
        if (specialtyText.endsWith(", ")) {
            specialtyText = specialtyText.substring(0, specialtyText.length() - 2);
        }
        return specialtyText;
    }
}
