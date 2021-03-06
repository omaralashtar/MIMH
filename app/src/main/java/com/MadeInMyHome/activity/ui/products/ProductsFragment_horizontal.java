package com.MadeInMyHome.activity.ui.products;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MadeInMyHome.activity.user.userProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.FragmentProductsHorizontalBinding;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;


public class ProductsFragment_horizontal extends Fragment {

    RecycleAdapterProduct recycleAdapterProduct;
    ProductsViewModel productsViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;
    private FragmentProductsHorizontalBinding binding;

    int next=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);
        productsViewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);

        binding = FragmentProductsHorizontalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        

        binding.recycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        setAdapter();

       

        return root;
    }

    public void setAdapter(){
        productsViewModel.getProducts(getActivity(),String.valueOf(next)).observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                recycleAdapterProduct =new RecycleAdapterProduct(getActivity(),products,"product",showUserProfileViewModel);
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