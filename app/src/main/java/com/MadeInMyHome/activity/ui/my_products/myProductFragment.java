package com.MadeInMyHome.activity.ui.my_products;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.MyProductFragmentBinding;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;

public class myProductFragment extends Fragment {

    private MyProductFragmentBinding binding;
    MyProductViewModel myProductViewModel;
    RecycleAdapterProduct myProductsAdapter;
    final int id = 1;
    //final int id = 1;
    //final int id = 1;

    public static myProductFragment newInstance() {
        return new myProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        myProductViewModel = ViewModelProviders.of(this).get(MyProductViewModel.class);
        binding = MyProductFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.myProductsRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        setAdapter();

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapter();
            }
        });

        return root;
    }

    public void setAdapter() {
        myProductViewModel.getMyProducts(getActivity(), String.valueOf(id)).observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> myProducts) {
                myProductsAdapter = new RecycleAdapterProduct(getActivity(), myProducts);
                binding.myProductsRecycle.setAdapter(myProductsAdapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}