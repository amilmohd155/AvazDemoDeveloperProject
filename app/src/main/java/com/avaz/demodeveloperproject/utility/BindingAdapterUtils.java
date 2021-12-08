package com.avaz.demodeveloperproject.utility;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.avaz.demodeveloperproject.R;
import com.bumptech.glide.Glide;

public class BindingAdapterUtils {

    private static final String TAG = "BindingConfig";

    @BindingAdapter(value = {"imageUrl", "drawableID", "isDefault"}, requireAll = false)
    public static void loadImage(ImageView view, String imageUrl, int drawableId, boolean isDefault) {
        Log.d(TAG, "loadImage: " + isDefault);
       if(isDefault) {
           Glide.with(view.getContext())
                   .load(drawableId)
                   .error(R.drawable.ic_apple)
                   .into(view);
       }else {
           Glide.with(view.getContext())
                   .load(imageUrl)
                   .error(R.drawable.ic_burger)
                   .into(view);
       }
    }

}
