package com.danilketov.testapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.danilketov.testapp.R;
import com.danilketov.testapp.fragment.SpecialtyFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new SpecialtyFragment())
                    .commit();
        }
    }
}
