package com.example.mvvmproject.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmproject.BR;
import com.example.mvvmproject.R;
import com.example.mvvmproject.data.model.Product;
import com.example.mvvmproject.databinding.ProductItemBinding;
import com.example.mvvmproject.presenter.ProductsContract;
import com.example.mvvmproject.presenter.ProductsPresenter;

import java.util.List;


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> implements ProductsContract.Actions {

    private List<Product> mList;
    private Context context;

    public MyRecyclerViewAdapter(List<Product> productList, Context ctx) {
        this.mList = productList;
        context = ctx;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProductItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.product_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = mList.get(position);
        holder.bind(product);
        holder.productItemBinding.setProductsContract(this);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void updateData(List<Product> products) {
        setList(products);
        notifyDataSetChanged();
    }

    private void setList(List<Product> products) {
        mList = products;
    }

    @Override
    public void loadProducts() {

    }

    @Override
    public void openProduct(Product product) {
        Toast.makeText(context, "You clicked " + product.name, Toast.LENGTH_LONG).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ProductItemBinding productItemBinding;

        public ViewHolder(ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }

        public void bind(Object obj) {
            //productItemBinding.setViewModel(new ProductsActivityViewModel());
            productItemBinding.setVariable(BR.productModel, obj);
            productItemBinding.executePendingBindings();
        }
    }

}