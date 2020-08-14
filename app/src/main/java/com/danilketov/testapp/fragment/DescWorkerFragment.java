package com.danilketov.testapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.danilketov.testapp.R;
import com.danilketov.testapp.databinding.FragmentDescWorkerBinding;
import com.danilketov.testapp.utils.Const;
import com.danilketov.testapp.utils.Filter;
import com.danilketov.testapp.utils.Parameters;
import com.squareup.picasso.Picasso;


public class DescWorkerFragment extends Fragment {

    FragmentDescWorkerBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDescWorkerBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setSettingsToolbar();
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

            binding.valueLastNameTextView.setText(lastName);
            binding.valueFirstNameTextView.setText(firstName);
            binding.valueAgeTextView.setText(age);
            binding.valueBirthdayTextView.setText(birthday);
            binding.valueSpecialTextView.setText(specialtyText);

            Picasso.get()
                    .load(avatar)
                    .fit()
                    .placeholder(R.drawable.no_avatar)
                    .into(binding.avatarCircleImageView);

        } else {
            Toast.makeText(getActivity(), R.string.frag_args_null, Toast.LENGTH_SHORT).show();
        }
    }

    public static Fragment newInstance(Parameters parameters) {
        Bundle args = new Bundle();
        args.putString(Const.KEY_WORKER_LAST_NAME, parameters.getLastName());
        args.putString(Const.KEY_WORKER_FIRST_NAME, parameters.getFirstName());
        args.putString(Const.KEY_WORKER_AGE, parameters.getAge());
        args.putString(Const.KEY_WORKER_BIRTHDAY, parameters.getBirthday());
        args.putString(Const.KEY_WORKER_AVATAR, parameters.getAvatar());
        args.putString(Const.KEY_SPECIALTY_JSON, parameters.getSpecialtyJSON());
        DescWorkerFragment fragment = new DescWorkerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private void setSettingsToolbar() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.toolbar_title_desc_worker);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
