package com.danilketov.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.testapp.R;
import com.danilketov.testapp.adapter.SpecialAdapter;
import com.danilketov.testapp.entity.Specialty;

public class SpecialtyFragment extends Fragment {

    private RecyclerView recyclerView;
    private SpecialAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_special, container, false);

        setSettingsToolbar();
        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.special_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        SpecialAdapter.OnInfoSpecialClickListener listener = new SpecialAdapter.OnInfoSpecialClickListener() {
            @Override
            public void onInfoSpecialClick(Specialty specialty) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new WorkerFragment())
                        .addToBackStack(null)
                        .commit();
            }
        };
        adapter = new SpecialAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    private void setSettingsToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title_specialty);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }
}
