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

public class SpecialAdapter extends RecyclerView.Adapter<SpecialAdapter.SpecialViewHolder> {

    private List<Specialty> specialties = new ArrayList<>();

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

    class SpecialViewHolder extends RecyclerView.ViewHolder {

        private TextView specialtyTextView;

        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);

            specialtyTextView = itemView.findViewById(R.id.specialty_text_view);
        }

        void bind(Specialty specialty) {
            specialtyTextView.setText(specialty.getName());
        }
    }
}
