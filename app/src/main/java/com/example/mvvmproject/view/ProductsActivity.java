package com.example.mvvmproject.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvmproject.R;
import com.example.mvvmproject.data.model.Product;
import com.example.mvvmproject.databinding.ProductsActivityBinding;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    private ProductsActivityViewModel mProductsActivityViewModel;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProductsActivityBinding productsActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.products_activity);

        mProductAdapter = new ProductAdapter(this, new ArrayList<Product>());
        mProductsActivityViewModel = new ProductsActivityViewModel(mProductAdapter);

        productsActivityBinding.setMyAdapter(mProductAdapter);
        productsActivityBinding.setViewModel(mProductsActivityViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProductsActivityViewModel.loadProducts();
    }
}
