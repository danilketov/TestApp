package com.danilketov.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.danilketov.testapp.R;
import com.danilketov.testapp.utils.Const;
import com.danilketov.testapp.utils.Filter;
import com.squareup.picasso.Picasso;


public class DescWorkerFragment extends Fragment {

    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView ageTextView;
    private TextView birthdayTextView;
    private TextView specialtyTextView;
    private ImageView circleAvatarImageView;

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
            String lastName = args.getString(Const.KEY_WORKER_LAST_NAME);
            String firstName = args.getString(Const.KEY_WORKER_FIRST_NAME);
            String age = args.getString(Const.KEY_WORKER_AGE);
            String birthday = args.getString(Const.KEY_WORKER_BIRTHDAY);
            String avatar = args.getString(Const.KEY_WORKER_AVATAR);
            String specialtyJSON = args.getString(Const.KEY_SPECIALTY_JSON);
            String specialtyText = Filter.getSpecialtyText(specialtyJSON);

            lastNameTextView.setText(lastName);
            firstNameTextView.setText(firstName);
            ageTextView.setText(age);
            birthdayTextView.setText(birthday);
            specialtyTextView.setText(specialtyText);

            Picasso.get()
                    .load(avatar)
                    .fit()
                    .placeholder(R.drawable.no_avatar)
                    .into(circleAvatarImageView);

        } else {
            Toast.makeText(getActivity(), R.string.frag_args_null, Toast.LENGTH_SHORT).show();
        }
    }

    public static Fragment newInstance(String lastName,
                                       String firstName,
                                       String age,
                                       String birthday,
                                       String avatar,
                                       String specialtyJSON) {
        Bundle args = new Bundle();
        args.putString(Const.KEY_WORKER_LAST_NAME, lastName);
        args.putString(Const.KEY_WORKER_FIRST_NAME, firstName);
        args.putString(Const.KEY_WORKER_AGE, age);
        args.putString(Const.KEY_WORKER_BIRTHDAY, birthday);
        args.putString(Const.KEY_WORKER_AVATAR, avatar);
        args.putString(Const.KEY_SPECIALTY_JSON, specialtyJSON);
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
        circleAvatarImageView = view.findViewById(R.id.avatar_circle_image_view);
    }
    private void setSettingsToolbar() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title_desc_worker);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
