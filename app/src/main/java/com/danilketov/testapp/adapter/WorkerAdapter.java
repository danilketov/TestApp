package com.danilketov.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.testapp.R;
import com.danilketov.testapp.entity.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {

    private List<Worker> workers = new ArrayList<>();

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_view_worker, parent, false);

        return new WorkerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        Worker worker = workers.get(position);
        holder.bind(worker);
    }

    @Override
    public int getItemCount() {
        return workers.size();
    }

    class WorkerViewHolder extends RecyclerView.ViewHolder {

        private TextView firstNameTextView;
        private TextView lastNameTextView;
        private TextView ageTextView;

        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTextView = itemView.findViewById(R.id.first_name_text_view);
            lastNameTextView = itemView.findViewById(R.id.last_name_text_view);
            ageTextView = itemView.findViewById(R.id.age_text_view);
        }

        void bind(Worker worker) {
            firstNameTextView.setText(worker.getFirstName());
            lastNameTextView.setText(worker.getLastName());
            ageTextView.setText(worker.getBirthday());
        }
    }
}
