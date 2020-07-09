package com.danilketov.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.danilketov.testapp.R;
import com.danilketov.testapp.utils.Filter;


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

        setSettingsToolbar();
        initView(view);
        getSetData();

        return view;
    }

    private void getSetData() {
        Bundle args = getArguments();
        if (args != null) {
            String lastName = args.getString("lName");
            String firstName = args.getString("fName");
            String age = args.getString("age");
            String birthday = args.getString("birthday");
            String specialtyJSON = args.getString("specialtyJSON");
            String specialtyText = Filter.getSpecialtyText(specialtyJSON);

            lastNameTextView.setText(lastName);
            firstNameTextView.setText(firstName);
            ageTextView.setText(age);
            birthdayTextView.setText(birthday);
            specialtyTextView.setText(specialtyText);

        } else {
            Toast.makeText(getActivity(), R.string.frag_args_null, Toast.LENGTH_SHORT).show();
        }
    }

    public static Fragment newInstance(String lastName,
                                       String firstName,
                                       String age,
                                       String birthday,
                                       String specialtyJSON) {
        Bundle args = new Bundle();
        args.putString("lName", lastName);
        args.putString("fName", firstName);
        args.putString("age", age);
        args.putString("birthday", birthday);
        args.putString("specialtyJSON", specialtyJSON);
        DescWorkerFragment fragment = new DescWorkerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void initView(View view) {
        firstNameTextView = view.findViewById(R.id.value_first_name_text_view);
        lastNameTextView = view.findViewById(R.id.value_last_name_text_view);
        ageTextView = view.findViewById(R.id.value_age_text_view);
        birthdayTextView = view.findViewById(R.id.value_birthday_text_view);
        specialtyTextView = view.findViewById(R.id.value_special_text_view);
    }
    private void setSettingsToolbar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title_desc_worker);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
