package com.example.mvvmproject.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvmproject.R;
import com.example.mvvmproject.data.model.Product;
import com.example.mvvmproject.databinding.ProductsActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductsActivity extends AppCompatActivity {
    private ProductsActivityViewModel mProductsActivityViewModel;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProductsActivityBinding productsActivityBinding =
                DataBindingUtil.setContentView(this, R.layout.products_activity);

        mProductsActivityViewModel = ViewModelProviders.of(this).get(ProductsActivityViewModel.class);

        mProductAdapter = new ProductAdapter(this, new ArrayList<Product>());
        productsActivityBinding.setMyAdapter(mProductAdapter);

        getAllProducts();
    }

    private void getAllProducts() {
        mProductsActivityViewModel.loadProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mProductAdapter.updateData(products);
            }
        });
    }
}
