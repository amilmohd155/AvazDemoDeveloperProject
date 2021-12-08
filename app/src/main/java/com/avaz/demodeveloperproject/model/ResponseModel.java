package com.avaz.demodeveloperproject.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseModel {

    @SerializedName("icons")
    @Expose
    private List<Icon> icons = null;

    public List<Icon> getIcons() {
        return icons;
    }

    public void setIcons(List<Icon> icons) {
        this.icons = icons;
    }

}