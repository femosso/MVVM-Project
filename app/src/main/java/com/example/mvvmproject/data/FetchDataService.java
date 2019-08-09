package com.example.mvvmproject.data;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmproject.data.model.Product;

import java.util.List;

public interface FetchDataService {
    MutableLiveData<List<Product>> getProducts();
}
