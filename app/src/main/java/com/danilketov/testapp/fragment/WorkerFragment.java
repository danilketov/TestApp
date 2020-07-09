package com.danilketov.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.testapp.R;
import com.danilketov.testapp.adapter.WorkerAdapter;
import com.danilketov.testapp.entity.Worker;

public class WorkerFragment extends Fragment {

    private RecyclerView recyclerView;
    private WorkerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_worker, container, false);

        initRecyclerView(view);

        return view;
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

}
