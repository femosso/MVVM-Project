package com.example.mvvmproject.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvvmproject.R;
import com.example.mvvmproject.data.RetrofitServiceImpl;
import com.example.mvvmproject.data.model.Product;
import com.example.mvvmproject.presenter.ProductsContract;
import com.example.mvvmproject.presenter.ProductsPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductsFragment extends Fragment implements ProductsContract.View {

    private ProductsContract.Actions mActionsListener;
    private ProductsAdapter mListAdapter;

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionsListener = new ProductsPresenter(this, new RetrofitServiceImpl());
        mListAdapter = new ProductsAdapter(new ArrayList<Product>(0), mProductListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.products_fragment, container, false);

        int numColumns = 1;

        RecyclerView recyclerView = root.findViewById(R.id.products_list);
        recyclerView.setAdapter(mListAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.loadProducts();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.loadProducts();
    }

    @Override
    public void showProducts(List<Product> productList) {
        mListAdapter.updateData(productList);
    }

    @Override
    public void setLoading(final boolean active) {
        final SwipeRefreshLayout srl = getView().findViewById(R.id.refresh_layout);
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showProduct(Product product) {
        Toast.makeText(getContext(), "Show product: " + product.name, Toast.LENGTH_LONG).show();
    }

    ProductListener mProductListener = new ProductListener() {
        @Override
        public void onProductClick(Product productClicked) {
            mActionsListener.openProduct(productClicked);
        }
    };

    public static class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

        private List<Product> mList;
        private ProductListener mProductListener;

        public ProductsAdapter(List<Product> products, ProductListener productListener) {
            mList = products;
            mProductListener = productListener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.product_item, parent, false);

            return new ViewHolder(noteView, mProductListener);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Product product = mList.get(position);

            Picasso.with(holder.productThumbnail.getContext())
                    .load(product.imageUrl)
                    .fit().centerCrop()
                    .placeholder(R.drawable.ic_insert_photo_black_48px)
                    .into(holder.productThumbnail);

            holder.productTitle.setText(product.name);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public Product getItem(int position) {
            return mList.get(position);
        }

        public void updateData(List<Product> products) {
            setList(products);
            notifyDataSetChanged();
        }

        private void setList(List<Product> products) {
            mList = products;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView productThumbnail;
            private TextView productTitle;

            private ProductListener mProductListener;

            public ViewHolder(View itemView, ProductListener productListener) {
                super(itemView);

                mProductListener = productListener;

                productTitle = itemView.findViewById(R.id.product_title);
                productThumbnail = itemView.findViewById(R.id.product_thumbnail);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                mProductListener.onProductClick(getItem(position));
            }
        }
    }

    public interface ProductListener {
        void onProductClick(Product productClicked);
    }
}
