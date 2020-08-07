package com.danilketov.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danilketov.testapp.App;
import com.danilketov.testapp.R;
import com.danilketov.testapp.adapter.SpecialtyAdapter;
import com.danilketov.testapp.api.ApiFactory;
import com.danilketov.testapp.api.ApiService;
import com.danilketov.testapp.databinding.FragmentSpecialBinding;
import com.danilketov.testapp.entity.Response;
import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.repository.DataRepository;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SpecialtyFragment extends Fragment {

    private FragmentSpecialBinding binding;
    private SpecialtyAdapter adapter;
    private DataRepository dataRepository;
    private Disposable disposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSpecialBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setSettingsToolbar();
        initRecyclerView();

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        disposable = apiService.getResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response response) {
                        ArrayList<Specialty> specialties = new ArrayList<>();
                        for (Worker workerInfo : response.getResponse()) {
                            specialties.addAll(workerInfo.getSpecialty());
                        }
                        adapter.addItems(specialties);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        dataRepository = App.getDataRepository();

        return view;
    }


    private void initRecyclerView() {
        binding.specialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.specialRecyclerView.setItemAnimator(null);
        SpecialtyAdapter.OnInfoSpecialClickListener listener = new SpecialtyAdapter.OnInfoSpecialClickListener() {
            @Override
            public void onInfoSpecialClick(Specialty specialty) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new WorkerFragment().newInstance(specialty.getName()))
                        .addToBackStack(null)
                        .commit();
            }
        };
        adapter = new SpecialtyAdapter(listener);
        binding.specialRecyclerView.setAdapter(adapter);
    }

    private void setSettingsToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title_specialty);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
