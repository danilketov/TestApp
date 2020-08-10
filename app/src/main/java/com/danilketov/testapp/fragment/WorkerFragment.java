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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.danilketov.testapp.R;
import com.danilketov.testapp.adapter.WorkerAdapter;
import com.danilketov.testapp.databinding.FragmentWorkerBinding;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.repository.DataRepository;
import com.danilketov.testapp.utils.Const;
import com.danilketov.testapp.utils.Converter;
import com.danilketov.testapp.utils.Filter;
import com.danilketov.testapp.viewmodel.WorkerViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;

import io.reactivex.rxjava3.disposables.Disposable;

public class WorkerFragment extends Fragment {

    private FragmentWorkerBinding binding;
    private WorkerAdapter adapter;
    private DataRepository dataRepository;
    private Disposable disposable;

    private WorkerViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentWorkerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setSettingsToolbar();
        initRecyclerView();
        getNameSpecialty();

        viewModel = ViewModelProviders.of(this).get(WorkerViewModel.class);
        viewModel.getWorkers().observe(this, new Observer<ArrayList<Worker>>() {
            @Override
            public void onChanged(ArrayList<Worker> workers) {
                if (workers != null && getNameSpecialty() != null) {
                    workers = Filter.getFilteredWorkers(workers, getNameSpecialty());
                    adapter.addItems(workers);
                } else {
                    Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
                }
            }
        });
        viewModel.loadData();

//        ApiFactory apiFactory = ApiFactory.getInstance();
//        ApiService apiService = apiFactory.getApiService();
//        disposable = apiService.getResponse()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Response>() {
//                    @Override
//                    public void accept(Response response) throws Throwable {
//                        ArrayList<Worker> result = response.getResponse();
//                        if (result != null && getNameSpecialty() != null) {
//                            result = Filter.getFilteredWorkers(result, getNameSpecialty());
//                            adapter.addItems(result);
//                        } else {
//                            Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Throwable {
//                        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//
//        dataRepository = App.getDataRepository();

        return view;
    }

    @Nullable
    private String getNameSpecialty() {
        Bundle args = getArguments();
        String nameSpecialty = null;
        if (args != null) {
            nameSpecialty = args.getString(Const.KEY_WORKER_SPECIALTY);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(nameSpecialty);
        } else {
            Toast.makeText(getActivity(), R.string.frag_args_null, Toast.LENGTH_SHORT).show();
        }
        return nameSpecialty;
    }

    private void setSettingsToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initRecyclerView() {
        binding.workerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.workerRecyclerView.setItemAnimator(null);
        WorkerAdapter.OnInfoWorkerClickListener listener = new WorkerAdapter.OnInfoWorkerClickListener() {
            @Override
            public void onInfoWorkerClick(Worker worker) {
                String lastName = Converter.getFormattedString(worker.getLastName());
                String firstName = Converter.getFormattedString(worker.getFirstName());
                String age = Converter.getFormattedAge(worker.getBirthday());
                String birthday = Converter.getFormattedBirthday(worker.getBirthday());
                String avatar = Converter.getAvatarWorker(worker.getAvatarUrl(), worker);
                String specialtyJSON = new Gson().toJson(worker.getSpecialty());
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new DescWorkerFragment()
                                .newInstance(lastName, firstName, age, birthday, avatar, specialtyJSON))
                        .addToBackStack(null)
                        .commit();
            }
        };
        adapter = new WorkerAdapter(listener);
        binding.workerRecyclerView.setAdapter(adapter);
    }

    public static Fragment newInstance(String specialty) {
        Bundle args = new Bundle();
        args.putString(Const.KEY_WORKER_SPECIALTY, specialty);
        WorkerFragment fragment = new WorkerFragment();
        fragment.setArguments(args);
        return fragment;
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
