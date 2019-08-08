package com.example.mvvmproject;

import com.example.mvvmproject.data.FetchDataService;
import com.example.mvvmproject.data.model.ProductSearchResult;

public class FakeFetchDataServiceImpl implements FetchDataService {
    @Override
    public void getProducts(FetchDataCallback<ProductSearchResult> callback) {
        callback.onLoaded(new ProductSearchResult());
    }
}
