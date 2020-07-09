package com.danilketov.testapp.network;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpClient {

    private static final String URL = "https://gitlab.65apps.com/65gb/static/raw/master/testTask.json";
    private OkHttpClient client = new OkHttpClient.Builder()
            .build();

    public ArrayList<Worker> getWorkersInfo() throws IOException {

        String requestUrl = Uri.parse(URL)
                .buildUpon()
                .build()
                .toString();

        String response = getResponse(requestUrl);

        return null;
    }

    public ArrayList<Specialty> getSpecialtyInfo() throws IOException {

        String requestUrl = Uri.parse(URL)
                .buildUpon()
                .build()
                .toString();

        String response = getResponse(requestUrl);

        return null;
    }

    @NonNull
    private String getResponse(String requestUrl) throws IOException {

        // Отправление запроса
        Request request = new Request.Builder()
                .url(requestUrl)
                .build();

        // Синхронный сетевой вызов
        Response responseRaw = client.newCall(request).execute();
        // Обработка ответа
        if (responseRaw.isSuccessful()) {
            String response = responseRaw.body().string();
            return response;
        } else {
            throw new IOException("Response is not successful");
        }
    }
}
