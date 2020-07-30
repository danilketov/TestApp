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

import com.danilketov.testapp.App;
import com.danilketov.testapp.R;
import com.danilketov.testapp.adapter.SpecialtyAdapter;
import com.danilketov.testapp.databinding.FragmentSpecialBinding;
import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.network.HttpClient;
import com.danilketov.testapp.repository.DataRepository;

import java.io.IOException;
import java.util.ArrayList;

public class SpecialtyFragment extends Fragment {

    FragmentSpecialBinding binding;
    private SpecialtyAdapter adapter;
    private HttpClient httpClient;
    private DataRepository dataRepository;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSpecialBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setSettingsToolbar();
        initRecyclerView();

        httpClient = new HttpClient();

        dataRepository = App.getDataRepository();

        updateContent();

        return view;
    }

    private void updateContent() {
        new GetSpecialtyAsyncTask().execute();
    }

    private class GetSpecialtyAsyncTask extends AsyncTask<String, Void, ArrayList<Specialty>> {

        @Override
        protected void onPreExecute() {
            binding.progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Specialty> doInBackground(String... queries) {

            try {
                return httpClient.getSpecialtyInfo();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Specialty> result) {
            binding.progressBar.setVisibility(View.GONE);

            if (result != null) {
                adapter.addItems(result);
            } else {
                Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initRecyclerView() {
        binding.specialRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
}
