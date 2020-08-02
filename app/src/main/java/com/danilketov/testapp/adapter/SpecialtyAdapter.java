package com.danilketov.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.testapp.R;
import com.danilketov.testapp.entity.Specialty;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyAdapter extends RecyclerView.Adapter<SpecialtyAdapter.SpecialViewHolder> {

    private List<Specialty> specialties = new ArrayList<>();

    private final OnInfoSpecialClickListener onInfoSpecialClickListener;

    public SpecialtyAdapter(OnInfoSpecialClickListener onInfoSpecialClickListener) {
        this.onInfoSpecialClickListener = onInfoSpecialClickListener;
    }

    public interface OnInfoSpecialClickListener {
        void onInfoSpecialClick(Specialty specialty);
    }

    @NonNull
    @Override
    public SpecialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_view_special, parent, false);

        return new SpecialViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialViewHolder holder, int position) {
        Specialty specialty = specialties.get(position);
        holder.bind(specialty);
    }

    @Override
    public int getItemCount() {
        return specialties.size();
    }

    public void addItems(List<Specialty> items) {
        addUniqueItems(items);
        notifyDataSetChanged();
    }

    private void addUniqueItems(List<Specialty> items) {
        for (Specialty specialty : items) {
            if (!specialties.contains(specialty)) {
                specialties.add(specialty);
            }
        }
    }

    class SpecialViewHolder extends RecyclerView.ViewHolder {

        private TextView specialtyTextView;

        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);

            specialtyTextView = itemView.findViewById(R.id.specialty_text_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPos = getAdapterPosition();
                    if (adapterPos != RecyclerView.NO_POSITION) {
                        Specialty specialty = specialties.get(adapterPos);
                        onInfoSpecialClickListener.onInfoSpecialClick(specialty);
                    }
                }
            });
        }

        void bind(Specialty specialty) {
            specialtyTextView.setText(specialty.getName());
        }
    }
}
