package com.danilketov.testapp.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danilketov.testapp.App;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.network.ApiFactory;
import com.danilketov.testapp.network.ApiService;
import com.danilketov.testapp.repository.DataRepository;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
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

    @SuppressWarnings("unchecked")
    private void insertWorkers(ArrayList<Worker> workers) {
        new GetWorkersAsyncTask().execute(workers);
    }

    private class GetWorkersAsyncTask extends AsyncTask<ArrayList<Worker>, Void, ArrayList<Worker>> {

        @Override
        protected void onPreExecute() {
            isLoading.setValue(true);
        }

        @Override
        protected ArrayList<Worker> doInBackground(ArrayList<Worker>... lists) {
            try {
                workers.postValue(dataRepository.getWorkers(lists[0]));
                return workers.getValue();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Worker> workers) {
            isLoading.setValue(false);
        }
    }

    public void loadData() {
        insertWorkers(null);
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        disposable = apiService.getResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> insertWorkers(response.getResponse()),
                        throwable -> isNetworkException.setValue(true));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
