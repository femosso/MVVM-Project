package com.example.mvvmproject.view;

import android.os.Handler;

import androidx.databinding.ObservableField;

public class ProductsActivityViewModel {

    public ObservableField<String> meuURL = new ObservableField<>();

    public ProductsActivityViewModel() {
    }

    // simula uma chamada na api
    public void showImage() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String url = "\"https://media.licdn.com/mpr/mpr/AAEAAQAAAAAAAAkjAAAAJGE5NjA2ZDRhLTc5MDUtNDJkMy1hNGZiLTViYzQwMzVhYjMwYw.png\"";
                meuURL.set(url);
            }
        }, 1500);

    }
}
