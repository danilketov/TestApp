package com.danilketov.testapp.utils;

import androidx.annotation.Nullable;

import com.danilketov.testapp.R;
import com.danilketov.testapp.entity.Worker;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Converter {

    // Форматирование дня рождения к единому формату
    @Nullable
    public static String getFormattedBirthday(@Nullable String dateString) {
        if (dateString == null || dateString.equals("")) {
            return "-";
        } else {
            DateFormat inputFormat1 = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            DateFormat inputFormat2 = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());

            Date date1;
            Date date2;

            DateFormat outputFormat = new SimpleDateFormat("dd.mm.yyyy", Locale.getDefault());

            try {
                date1 = inputFormat1.parse(dateString);
                if (date1.getTime() > 0) {
                    return outputFormat.format(date1) + Const.WORKER_BIRTHDAY_YEAR_END;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                date2 = inputFormat2.parse(dateString);
                if (date2.getTime() > 0) {
                    return outputFormat.format(date2) + Const.WORKER_BIRTHDAY_YEAR_END;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    //Преобразование возраста из дня рождения
    @NotNull
    public static String getFormattedAge(@Nullable String birthdayString) {

        if (birthdayString == null || birthdayString.equals("")) {
            return "-";
        } else {
            String birthday = getFormattedBirthday(birthdayString);

            Date date = null;

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd.mm.yyyy", Locale.getDefault());

            try {
                date = inputFormat.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar dob = Calendar.getInstance();
            Calendar today = Calendar.getInstance();

            dob.setTime(date);

            int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
            if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                age--;
            }

            String result = age + Const.WORKER_AGE_END;

            return result;
        }
    }

    // Форматирование строки с заглавной буквы
    @NotNull
    public static String getFormattedString(@Nullable String str) {
        if (str == null || str.equals("")) {
            return "-";
        } else {
            String result = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
            return result;
        }
    }

    // Отображение фото работника
    @NotNull
    public static String getAvatarWorker(@Nullable String avatarUrl, @NotNull Worker worker) {
        if (avatarUrl == null || avatarUrl.equals("")) {
            String result = String.valueOf(R.drawable.no_avatar);
            return result;
        } else {
            String result = worker.getAvatarUrl();
            return result;

        }
    }
}
