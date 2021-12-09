package com.avaz.demodeveloperproject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DishModel implements Serializable, Parcelable {

    @SerializedName("icon_url")
    @Expose
    private String imageSource;

    private int drawableSource;

    @SerializedName("term")
    @Expose
    private String name;

    private boolean isChecked;

    public DishModel() {
    }

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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    protected DishModel(Parcel in) {

        imageSource = in.readString();
        drawableSource = in.readInt();
        name = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageSource);
        dest.writeInt(drawableSource);
        dest.writeString(name);
    }

    public static final Creator<DishModel> CREATOR = new Creator<DishModel>() {
        @Override
        public DishModel createFromParcel(Parcel source) {
            return new DishModel(source);
        }

        @Override
        public DishModel[] newArray(int size) {
            return new DishModel[size];
        }
    };

}
