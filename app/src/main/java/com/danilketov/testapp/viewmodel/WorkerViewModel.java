package com.danilketov.testapp.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danilketov.testapp.App;
import com.danilketov.testapp.api.ApiFactory;
import com.danilketov.testapp.api.ApiService;
import com.danilketov.testapp.entity.Response;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.repository.DataRepository;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WorkerViewModel extends ViewModel {

    private DataRepository dataRepository = App.getDataRepository();

    private MutableLiveData<ArrayList<Worker>> workers = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNetworkException = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private Disposable disposable;

    public LiveData<ArrayList<Worker>> getWorkers() {
        return workers;
    }

    public LiveData<Boolean> isNetworkException() {
        return isNetworkException;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public void insertWorkers(ArrayList<Worker> workers) {
        new GetRepositoriesAsyncTask().execute(workers);

    }

    private class GetRepositoriesAsyncTask extends AsyncTask<ArrayList<Worker>, Void, ArrayList<Worker>> {
        @Override
        protected ArrayList<Worker> doInBackground(ArrayList<Worker>... lists) {
            try {
                return dataRepository.getWorkers(lists[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        disposable = apiService.getResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) throws Throwable {
                        insertWorkers(response.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
