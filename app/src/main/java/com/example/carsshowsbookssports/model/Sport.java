package com.example.carsshowsbookssports.model;

public class Sport {
    String name;
    int skill_level;
    int fun_rating;
    int imageResId;

    public Sport(String name, int skill_level, int fun_rating, int imageResId) {
        this.name = name;
        this.skill_level = skill_level;
        this.fun_rating = fun_rating;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkill_level() {
        return skill_level;
    }

    public void setSkill_level(int skill_level) {
        this.skill_level = skill_level;
    }

    public int getFun_rating() {
        return fun_rating;
    }

    public void setFun_rating(int fun_rating) {
        this.fun_rating = fun_rating;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", skill_level=" + skill_level +
                ", fun_rating=" + fun_rating +
                ", imageResId=" + imageResId +
                '}';
    }
}
