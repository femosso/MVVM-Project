package com.example.mvvmproject;

import com.example.mvvmproject.data.FetchDataService;
import com.example.mvvmproject.data.model.Product;
import com.example.mvvmproject.data.model.ProductSearchResult;
import com.example.mvvmproject.presenter.ProductsContract;
import com.example.mvvmproject.presenter.ProductsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

public class ProductsPresenterTest {

    private static ArrayList<Product> PRODUCTS = new ArrayList<Product>() {{
        add(new Product("product 1", "thumbnail 1"));
        add(new Product("product 2", "thumbnail 2"));
        add(new Product("product 3", "thumbnail 3"));
    }};

    private static ProductSearchResult PRODUCT_SEARCH_RESULT = new ProductSearchResult(PRODUCTS);

    @Mock
    private FakeFetchDataServiceImpl mFetchDataService;

    @Mock
    private ProductsContract.View mProductsView;

    @Captor
    private ArgumentCaptor<FetchDataService.FetchDataCallback> mFetchDataCallbackCaptor;

    private ProductsPresenter mProductsPresenter;

    @Before
    public void setupProductsPresenter() {
        MockitoAnnotations.initMocks(this);

        mProductsPresenter = new ProductsPresenter(mProductsView, mFetchDataService);
    }

    @Test
    public void loadProductsAndDisplay() {
        mProductsPresenter.loadProducts();

        verify(mProductsView).setLoading(true);

        // simulate callback to fetch data
        verify(mFetchDataService).getProducts(mFetchDataCallbackCaptor.capture());
        mFetchDataCallbackCaptor.getValue().onLoaded(PRODUCT_SEARCH_RESULT);

        verify(mProductsView).setLoading(false);
        verify(mProductsView).showProducts(PRODUCT_SEARCH_RESULT.products);
    }

    @Test
    public void openProductAndDisplay() {
        for (Product item : PRODUCTS) {
            mProductsPresenter.openProduct(item);
            verify(mProductsView).showProduct(item);
        }
    }
}
