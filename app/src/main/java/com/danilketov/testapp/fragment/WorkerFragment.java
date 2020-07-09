package com.danilketov.testapp.fragment;

import android.os.AsyncTask;
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
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.testapp.R;
import com.danilketov.testapp.adapter.WorkerAdapter;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.network.HttpClient;

import java.io.IOException;
import java.util.ArrayList;

public class WorkerFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkerAdapter adapter;
    private HttpClient httpClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_worker, container, false);

        setSettingsToolbar();
        initRecyclerView(view);

        httpClient = new HttpClient();

        updateContent();

        return view;
    }

    private void updateContent() {
        new GetWorkerAsyncTask().execute();
    }

    private class GetWorkerAsyncTask extends AsyncTask<String, Void, ArrayList<Worker>> {

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

            if(result != null) {
                adapter.addItems(result);
            } else {
                Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.worker_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        WorkerAdapter.OnInfoWorkerClickListener listener = new WorkerAdapter.OnInfoWorkerClickListener() {
            @Override
            public void onInfoWorkerClick(Worker worker) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new DescWorkerFragment())
                        .addToBackStack(null)
                        .commit();
            }
        };
        adapter = new WorkerAdapter(listener);
        recyclerView.setAdapter(adapter);
    }
    private void setSettingsToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
