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

import java.util.ArrayList;
import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements ProductsContract.Actions {

    private List<Product> mList;
    private Context mContext;

    public ProductListAdapter(Context ctx) {
        mContext = ctx;
        mList = new ArrayList<>();
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void openProduct(Product product) {
        Toast.makeText(mContext, "You clicked " + product.name, Toast.LENGTH_LONG).show();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ProductItemBinding productItemBinding;

        ViewHolder(ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());
            this.productItemBinding = productItemBinding;
        }

        void bind(Object obj) {
            productItemBinding.setVariable(BR.productModel, obj);
            productItemBinding.executePendingBindings();
        }
    }
}