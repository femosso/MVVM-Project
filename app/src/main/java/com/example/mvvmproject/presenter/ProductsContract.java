package com.example.mvvmproject.presenter;

import com.example.mvvmproject.data.model.Product;

import java.util.List;

public interface ProductsContract {

    interface View {
        void showProducts(List<Product> productList);
        void setLoading(boolean active);
        void showProduct(Product product);
    }

    interface Actions {
        void openProduct(Product product);
    }
}
