package com.danilketov.testapp.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.testapp.R;
import com.danilketov.testapp.adapter.WorkerAdapter;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.network.HttpClient;
import com.danilketov.testapp.utils.Converter;
import com.danilketov.testapp.utils.Filter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class WorkerFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkerAdapter adapter;
    private HttpClient httpClient;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_worker, container, false);

        setSettingsToolbar();
        initRecyclerView(view);
        getNameSpecialty();

        progressBar = view.findViewById(R.id.progress_bar);

        httpClient = new HttpClient();

        updateContent();

        return view;
    }

    private void updateContent() {
        new GetWorkerAsyncTask().execute();
    }

    private class GetWorkerAsyncTask extends AsyncTask<String, Void, ArrayList<Worker>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected ArrayList<Worker> doInBackground(String... queries) {
            try {
                return httpClient.getWorkersInfo();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Worker> result) {
            progressBar.setVisibility(View.GONE);

            if(result != null && getNameSpecialty()!= null) {
                result = Filter.getFilteredWorkers(result, getNameSpecialty());
                adapter.addItems(result);
            } else {
                Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getNameSpecialty() {
        Bundle args = getArguments();
        String nameSpecialty = null;
        if (args != null) {
            nameSpecialty = args.getString("nameSpec");
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

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.worker_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                        .replace(R.id.fragment_container, new DescWorkerFragment().newInstance(lastName, firstName, age, birthday, avatar, specialtyJSON))
                        .addToBackStack(null)
                        .commit();
            }
        };
        adapter = new WorkerAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    public static Fragment newInstance(String specialty) {
        Bundle args = new Bundle();
        args.putString("nameSpec", specialty);
        WorkerFragment fragment = new WorkerFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
