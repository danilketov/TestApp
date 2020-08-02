package com.danilketov.testapp.utils;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.danilketov.testapp.entity.Worker;

import java.util.List;

public class WorkerDiffUtilCallback extends DiffUtil.Callback {

    private final List<Worker> oldList;
    private final List<Worker> newList;

    public WorkerDiffUtilCallback(List<Worker> oldList, List<Worker> newList) {
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
        Worker oldWorker = oldList.get(oldItemPosition);
        Worker newWorker = newList.get(newItemPosition);
        return oldWorker.getId() == newWorker.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Worker oldWorker = oldList.get(oldItemPosition);
        Worker newWorker = newList.get(newItemPosition);
        return oldWorker.getLastName().equals(newWorker.getLastName()) &&
                oldWorker.getFirstName().equals(newWorker.getFirstName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
