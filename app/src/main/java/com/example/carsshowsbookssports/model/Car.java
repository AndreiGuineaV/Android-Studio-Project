package com.example.carsshowsbookssports.model;

public class Car {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", year=" + year +
                ", imageResId=" + imageResId +
                '}';
    }
}
