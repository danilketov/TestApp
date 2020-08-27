package com.danilketov.testapp.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danilketov.testapp.App;
import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.network.ApiFactory;
import com.danilketov.testapp.network.ApiService;
import com.danilketov.testapp.repository.DataRepository;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SpecialtyViewModel extends ViewModel {

    private DataRepository dataRepository = App.getDataRepository();
    private MutableLiveData<ArrayList<Specialty>> specialties = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNetworkException = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private Disposable disposable;

    public LiveData<ArrayList<Specialty>> getSpecialties() {
        return specialties;
    }

    public LiveData<Boolean> isNetworkException() {
        return isNetworkException;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    @SuppressWarnings("unchecked")
    private void insertSpecialties(ArrayList<Specialty> specialties) {
        new GetSpecialtiesAsyncTask().execute(specialties);
    }

    private class GetSpecialtiesAsyncTask extends AsyncTask<ArrayList<Specialty>, Void, ArrayList<Specialty>> {

        @Override
        protected void onPreExecute() {
            isLoading.setValue(true);
        }

        @Override
        protected ArrayList<Specialty> doInBackground(ArrayList<Specialty>... lists) {
            try {
                specialties.postValue(dataRepository.getSpecialties(lists[0]));
                return specialties.getValue();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Specialty> specialties) {
            isLoading.setValue(false);
        }
    }

    public void loadData() {
        insertSpecialties(null);
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        disposable = apiService.getResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    ArrayList<Specialty> specialties = new ArrayList<>();
                    for (Worker workerInfo : response.getResponse()) {
                        specialties.addAll(workerInfo.getSpecialty());
                    }
                    insertSpecialties(specialties);
                }, throwable -> isNetworkException.setValue(true));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
