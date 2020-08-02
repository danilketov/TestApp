package com.danilketov.testapp.utils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.danilketov.testapp.entity.Specialty;

import java.util.List;

public class SpecialtyDiffUtilCallback extends DiffUtil.Callback {

    private final List<Specialty> oldList;
    private final List<Specialty> newList;

    public SpecialtyDiffUtilCallback(List<Specialty> oldList, List<Specialty> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Specialty oldSpecialty = oldList.get(oldItemPosition);
        Specialty newSpecialty = newList.get(newItemPosition);
        return oldSpecialty.getId() == newSpecialty.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Specialty oldSpecialty = oldList.get(oldItemPosition);
        Specialty newSpecialty = newList.get(newItemPosition);
        return oldSpecialty.getName().equals(newSpecialty.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
