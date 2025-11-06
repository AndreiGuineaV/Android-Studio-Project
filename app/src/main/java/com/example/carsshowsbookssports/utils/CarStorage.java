package com.example.carsshowsbookssports.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.carsshowsbookssports.models.Car;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CarStorage {

    public static final String PREF_NAME = "car_prefs";
    public static final String KEY_CARS = "saved_cars";

    public static void saveCars(Context context, ArrayList<Car> cars)
    {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(cars);

        editor.putString(KEY_CARS, json);
        editor.apply();
    }

    public static ArrayList<Car> loadCars(Context context){
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(KEY_CARS, null);

        if(json == null)
        {
            return new ArrayList<>();
        }

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Car>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public static void clearCars(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}
