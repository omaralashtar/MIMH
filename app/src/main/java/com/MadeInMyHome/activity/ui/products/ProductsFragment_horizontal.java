package com.MadeInMyHome.activity.ui.products;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MadeInMyHome.R;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.FragmentProductsHorizontalBinding;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;


public class ProductsFragment_horizontal extends Fragment {

    RecycleAdapterProduct recycleAdapterProduct;
    ProductsViewModel productsViewModel;
    private FragmentProductsHorizontalBinding binding;

    int next=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        productsViewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);
        binding = FragmentProductsHorizontalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        

        binding.recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        setAdapter();

       

        return root;
    }

    public void setAdapter(){
        productsViewModel.getProducts(getActivity(),String.valueOf(next)).observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                recycleAdapterProduct =new RecycleAdapterProduct(getActivity(),products);
                binding.recycle.setAdapter(recycleAdapterProduct);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}