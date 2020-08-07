package com.danilketov.testapp.api;

import com.danilketov.testapp.entity.Response;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiService {
    @GET("65gb/static/raw/master/testTask.json")
    Observable<Response> getResponse();
}
