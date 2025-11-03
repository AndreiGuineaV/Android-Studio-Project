package com.example.carsshowsbookssports.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Car implements Serializable, Parcelable {

    String name;
    String brand;
    int year;
    int imageResId;

    public Car(String name, String brand, int year, int imageResId) {
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.imageResId = imageResId;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Car(Parcel in) {
        name = in.readString();
        brand = in.readString();
        year = in.readInt();
        imageResId = in.readInt();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(brand);
        dest.writeInt(year);
        dest.writeInt(imageResId);
    }

    // GETTERE
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public int getYear() { return year; }
    public int getImageResId() { return imageResId; }
}

