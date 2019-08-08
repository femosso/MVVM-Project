package com.example.mvvmproject.data;

import com.example.mvvmproject.data.model.ProductSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitEndpoint {
    @GET("femosso/products-dataset/master/data.txt")
    Call<ProductSearchResult> query();
}