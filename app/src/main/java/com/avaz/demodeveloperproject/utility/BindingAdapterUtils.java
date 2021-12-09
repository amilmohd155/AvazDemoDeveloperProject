package com.avaz.demodeveloperproject.utility;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.avaz.demodeveloperproject.R;
import com.bumptech.glide.Glide;

public class BindingAdapterUtils {

    private static final String TAG = "BindingConfig";

    @BindingAdapter(value = {"imageUrl", "drawableID", "isDefault"}, requireAll = false)
    public static void loadImage(ImageView view, String imageUrl, int drawableId, boolean isDefault) {
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

    @BindingAdapter("android:layout_width")
    public static void setWidth(View view, boolean isFinalList) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = isFinalList ? MATCH_PARENT : WRAP_CONTENT;
        view.setLayoutParams(params);
    }

}
