package com.example.mvvmproject.data;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmproject.data.model.Product;
import com.example.mvvmproject.data.model.ProductSearchResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitServiceImpl implements FetchDataService {

    private RetrofitEndpoint mRetrofitEndpoint;
    private MutableLiveData<List<Product>> mMutableLiveData = new MutableLiveData<>();

    public RetrofitServiceImpl() {
        mRetrofitEndpoint = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    @Override
    public MutableLiveData<List<Product>> getProducts() {
        Call<ProductSearchResult> callProduct = mRetrofitEndpoint.query();
        callProduct.enqueue(new Callback<ProductSearchResult>() {
            @Override
            public void onResponse(Call<ProductSearchResult> call, Response<ProductSearchResult> response) {
                if (response.code() == 200) {
                    ProductSearchResult searchResult = response.body();
                    mMutableLiveData.setValue(searchResult.products);
                }
            }

            @Override
            public void onFailure(Call<ProductSearchResult> call, Throwable t) {
                mMutableLiveData.setValue(null);
            }
        });
        return mMutableLiveData;
    }
}
