package com.example.mvvmproject.presenter;

import com.example.mvvmproject.data.model.Product;

public class ProductsPresenter implements ProductsContract.Actions {

    private ProductsContract.View mProductsView;

    public ProductsPresenter(ProductsContract.View productsView) {
        mProductsView = productsView;
    }

    @Override
    public void openProduct(Product product) {
        mProductsView.showProduct(product);
    }
}
