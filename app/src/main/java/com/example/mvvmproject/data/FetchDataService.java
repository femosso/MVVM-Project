package com.example.mvvmproject.data;

import com.example.mvvmproject.data.model.ProductSearchResult;

public interface FetchDataService {

    interface FetchDataCallback<T> {
        void onLoaded(T data);
    }

    void getProducts(FetchDataCallback<ProductSearchResult> callback);
}
