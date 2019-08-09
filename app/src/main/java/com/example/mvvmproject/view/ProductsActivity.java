package com.example.mvvmproject.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvmproject.R;
import com.example.mvvmproject.data.model.Product;
import com.example.mvvmproject.databinding.ProductsActivityBinding;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    private ProductsActivityBinding binding;
    //private ProductsActivityViewModel viewModel;

    private static ArrayList<Product> PRODUCTS = new ArrayList<Product>() {{
        add(new Product("product 1", "thumbnail 1"));
        add(new Product("product 2", "thumbnail 2"));
        add(new Product("product 3", "thumbnail 3"));
    }};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // instancia o viewModel
        //viewModel = new ProductsActivityViewModel();

        // vincula a view ao viewModel
        binding = DataBindingUtil.setContentView(this, R.layout.products_activity);
        //binding.setViewModel(viewModel); // vincula o viewModel
        //binding.executePendingBindings();

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(PRODUCTS, this);
        binding.setMyAdapter(myRecyclerViewAdapter);
    }
}
