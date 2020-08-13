package com.danilketov.testapp.utils;

import android.os.Bundle;

import com.danilketov.testapp.fragment.DescWorkerFragment;

import org.jetbrains.annotations.NotNull;

public class Arguments {

    @NotNull
    public static DescWorkerFragment getDescWorkerFragment(String lastName,
                                                           String firstName,
                                                           String age,
                                                           String birthday,
                                                           String avatar,
                                                           String specialtyJSON,
                                                           Bundle args) {
        args.putString(Const.KEY_WORKER_LAST_NAME, lastName);
        args.putString(Const.KEY_WORKER_FIRST_NAME, firstName);
        args.putString(Const.KEY_WORKER_AGE, age);
        args.putString(Const.KEY_WORKER_BIRTHDAY, birthday);
        args.putString(Const.KEY_WORKER_AVATAR, avatar);
        args.putString(Const.KEY_SPECIALTY_JSON, specialtyJSON);
        return new DescWorkerFragment();
    }
}
