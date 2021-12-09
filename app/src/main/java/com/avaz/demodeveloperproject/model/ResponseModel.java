package com.avaz.demodeveloperproject.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseModel {

    @SerializedName("icons")
    @Expose
    private List<DishModel> icons = null;

    public List<DishModel> getIcons() {
        return icons;
    }

    public void setIcons(List<DishModel> icons) {
        this.icons = icons;
    }

}