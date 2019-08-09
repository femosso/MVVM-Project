package com.example.mvvmproject.view;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.example.mvvmproject.R;
import com.squareup.picasso.Picasso;

public class ImageViewBindAdapter {

    @BindingAdapter("showImage")
    public static void bindUrlToImageview(ImageView imageView, String url) {
        Picasso.with(imageView.getContext())
                .load(url)
                .fit().centerCrop()
                .placeholder(R.drawable.ic_insert_photo_black_48px)
                .into(imageView);
    }
}