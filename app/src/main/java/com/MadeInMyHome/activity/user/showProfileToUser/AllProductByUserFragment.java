package com.MadeInMyHome.activity.user.showProfileToUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.ui.my_products.MyProductViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.FragmentAllProductByUserBinding;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;


public class AllProductByUserFragment extends Fragment {

    private FragmentAllProductByUserBinding binding;
    MyProductViewModel myProductViewModel;
    RecycleAdapterProduct recycleAdapterProduct;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentAllProductByUserBinding.inflate(inflater, container, false);
        myProductViewModel=ViewModelProviders.of(this).get(MyProductViewModel.class);
        View root=binding.getRoot();
        binding.myProductsRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));





        myProductViewModel.getMyProducts(getActivity(), getActivity().getIntent().getExtras().getString("id")).observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> myProducts) {
                recycleAdapterProduct = new RecycleAdapterProduct(getActivity(), myProducts,"product");
                binding.myProductsRecycle.setAdapter(recycleAdapterProduct);
            }
        });





        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}