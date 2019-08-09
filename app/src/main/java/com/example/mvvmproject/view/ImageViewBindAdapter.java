package com.example.mvvmproject.view;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ImageViewBindAdapter {

    @BindingAdapter("showImage")
    public static void bindUrlToImageview(ImageView imageView, String URL) {
        Log.d("Felipe", "loading: " + URL);
        Glide.with(imageView.getContext())
                .load(URL)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
}