package com.danilketov.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.danilketov.testapp.R;


public class DescWorkerFragment extends Fragment {

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView ageTextView;
    private TextView birthdayTextView;
    private TextView specialtyTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_desc_worker, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        firstNameTextView = view.findViewById(R.id.value_first_name_text_view);
        lastNameTextView = view.findViewById(R.id.value_last_name_text_view);
        ageTextView = view.findViewById(R.id.value_age_text_view);
        birthdayTextView = view.findViewById(R.id.value_birthday_text_view);
        specialtyTextView = view.findViewById(R.id.value_special_text_view);
    }
}
