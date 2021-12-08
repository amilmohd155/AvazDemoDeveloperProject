package com.avaz.demodeveloperproject.utility;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapterUtils {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String ImageUrl) {
        Glide.with(view.getContext())
                .load(ImageUrl)
                .into(view);
    }

}
