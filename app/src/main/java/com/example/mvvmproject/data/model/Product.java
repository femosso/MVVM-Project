package com.example.mvvmproject.data.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name")
    public String name;

    @SerializedName("thumbnail")
    public String imageUrl;

    public Product() {
    }

    public Product(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
}