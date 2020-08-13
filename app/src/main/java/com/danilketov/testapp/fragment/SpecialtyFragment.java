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
import com.danilketov.testapp.adapter.SpecialtyAdapter;
import com.danilketov.testapp.databinding.FragmentSpecialBinding;
import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.viewmodel.SpecialtyViewModel;

import java.util.ArrayList;

public class SpecialtyFragment extends Fragment {

    private FragmentSpecialBinding binding;
    private SpecialtyAdapter adapter;
    private SpecialtyViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentSpecialBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setSettingsToolbar();
        initRecyclerView();

        viewModel = ViewModelProviders.of(this).get(SpecialtyViewModel.class);
        viewModel.getSpecialties().observe(this, new Observer<ArrayList<Specialty>>() {
            @Override
            public void onChanged(ArrayList<Specialty> specialties) {
                adapter.addItems(specialties);
            }
        });
        viewModel.loadData();

        viewModel.isLoading().observe(this, (isLoading) -> {
            if (isLoading != null) {
                binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        viewModel.isNetworkException().observe(this, (isException) -> {
            if (isException != null && isException) {
                Toast.makeText(getActivity(), R.string.error, Toast.LENGTH_SHORT).show();
            }
        });

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
}
