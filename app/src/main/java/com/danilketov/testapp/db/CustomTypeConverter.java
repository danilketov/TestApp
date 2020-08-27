package com.danilketov.testapp.db;

import androidx.room.TypeConverter;

import com.danilketov.testapp.entity.Specialty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CustomTypeConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Specialty> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Specialty>>() {
        }.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Specialty> someObjects) {
        return gson.toJson(someObjects);
    }
}
