package com.example.mvvmproject.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmproject.data.FetchDataService;
import com.example.mvvmproject.data.RetrofitServiceImpl;
import com.example.mvvmproject.data.model.Product;

import java.util.List;

public class ProductsActivityViewModel extends AndroidViewModel {

    private  FetchDataService fetchDataService;

    public ProductsActivityViewModel(Application application) {
        super(application);
        fetchDataService = new RetrofitServiceImpl();
    }

    public LiveData<List<Product>> loadProducts() {
        return fetchDataService.getProducts();
    }
}
