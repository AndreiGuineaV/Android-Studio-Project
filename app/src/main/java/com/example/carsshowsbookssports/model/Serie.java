package com.example.carsshowsbookssports.model;

public class Serie {

    String name;
    int rating;
    int imageResId;

    public Serie(String name, int rating, int imageResId) {
        this.name = name;
        this.rating = rating;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Serie{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", imageResId=" + imageResId +
                '}';
    }
}
