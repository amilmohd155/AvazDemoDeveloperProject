package com.avaz.demodeveloperproject.model;

public class DishModel {

    private String imageSource;
    private int drawableSource;
    private String name;

    public DishModel(int drawableSource, String name) {
        this.drawableSource = drawableSource;
        this.name = name;
    }

    public DishModel(String imageSource, String name) {
        this.imageSource = imageSource;
        this.name = name;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawableSource() {
        return drawableSource;
    }

    public void setDrawableSource(int drawableSource) {
        this.drawableSource = drawableSource;
    }
}
