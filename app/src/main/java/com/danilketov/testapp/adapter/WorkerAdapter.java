package com.danilketov.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.testapp.R;
import com.danilketov.testapp.entity.Worker;
import com.danilketov.testapp.utils.Converter;
import com.danilketov.testapp.utils.WorkerDiffUtilCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {

    private List<Worker> workers = new ArrayList<>();

    private final OnInfoWorkerClickListener onInfoWorkerClickListener;

    public WorkerAdapter(OnInfoWorkerClickListener onInfoWorkerClickListener) {
        this.onInfoWorkerClickListener = onInfoWorkerClickListener;
    }

    public interface OnInfoWorkerClickListener {
        void onInfoWorkerClick(Worker worker);
    }

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

    public void addItems(List<Worker> items) {
        checkUpdateItems(items);
    }

    private void checkUpdateItems(List<Worker> items) {
        final WorkerDiffUtilCallback diffCallback = new WorkerDiffUtilCallback(this.workers, items);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.workers.clear();
        this.workers.addAll(items);
        diffResult.dispatchUpdatesTo(this);
    }

    class WorkerViewHolder extends RecyclerView.ViewHolder {

        private TextView firstNameTextView;
        private TextView lastNameTextView;
        private TextView ageTextView;
        private ImageView avatarImageView;

        public WorkerViewHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTextView = itemView.findViewById(R.id.first_name_text_view);
            lastNameTextView = itemView.findViewById(R.id.last_name_text_view);
            ageTextView = itemView.findViewById(R.id.age_text_view);
            avatarImageView = itemView.findViewById(R.id.avatar_circle_image_view);

            itemView.setOnClickListener(v -> {
                int adapterPos = getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    Worker worker = workers.get(adapterPos);
                    onInfoWorkerClickListener.onInfoWorkerClick(worker);
                }
            });
        }

        void bind(Worker worker) {
            firstNameTextView.setText(Converter.getFormattedString(worker.getFirstName()));
            lastNameTextView.setText(Converter.getFormattedString(worker.getLastName()));
            ageTextView.setText(Converter.getFormattedAge(worker.getBirthday()));

            String avatarUrl = worker.getAvatarUrl();
            Picasso.get()
                    .load(Converter.getAvatarWorker(avatarUrl, worker))
                    .fit()
                    .placeholder(R.drawable.no_avatar)
                    .into(avatarImageView);
        }
    }
}
