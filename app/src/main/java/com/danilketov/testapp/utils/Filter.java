package com.danilketov.testapp.utils;

import com.danilketov.testapp.entity.Specialty;
import com.danilketov.testapp.entity.Worker;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

public class Filter {

    // Фильтр отображения работников по выбранным специальностям
    public static ArrayList<Worker> getFilteredWorkers(ArrayList<Worker> result, String nameSpecialty){
        Iterator<Worker> i = result.iterator();
        while (i.hasNext()) {
            Worker worker = i.next();
            boolean suitable = false;
            for(Specialty oneSpecialty: worker.getSpecialty()){
                if(oneSpecialty.getName().equals(nameSpecialty)){
                    suitable = true;
                }
            }
            if(!suitable)
                i.remove();
        }
        return result;
    }
}
