package com.example.carsshowsbookssports.model;

public class Book {
    String name;
    int reading_time;
    int rating;
    int imageResId;

    public Book(String name, int reading_time, int rating, int imageResId) {
        this.name = name;
        this.reading_time = reading_time;
        this.rating = rating;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReading_time() {
        return reading_time;
    }

    public void setReading_time(int reading_time) {
        this.reading_time = reading_time;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", reading_time=" + reading_time +
                ", rating=" + rating +
                ", imageResId=" + imageResId +
                '}';
    }
}
