package com.example.mvvmproject.view;

import androidx.databinding.ObservableBoolean;

import com.example.mvvmproject.data.FetchDataService;
import com.example.mvvmproject.data.RetrofitServiceImpl;
import com.example.mvvmproject.data.model.ProductSearchResult;

public class ProductsActivityViewModel {

    public ObservableBoolean isLoading = new ObservableBoolean();

    private FetchDataService fetchDataService;
    private ProductAdapter mProductAdapter;

    public ProductsActivityViewModel(ProductAdapter productAdapter) {
        mProductAdapter = productAdapter;
        fetchDataService = new RetrofitServiceImpl();
    }

    public void loadProducts() {
        fetchDataService.getProducts(new FetchDataService.FetchDataCallback<ProductSearchResult>() {
            @Override
            public void onLoaded(ProductSearchResult data) {
                isLoading.set(false);
                mProductAdapter.updateData(data.products);
            }
        });
    }

    public void onRefresh() {
        isLoading.set(true);
        loadProducts();
    }
}