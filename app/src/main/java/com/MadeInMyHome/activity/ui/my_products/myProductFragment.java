package com.MadeInMyHome.activity.ui.my_products;

import android.content.Intent;
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

import com.MadeInMyHome.activity.add_update_product.AddUpdateProductActivity;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.FragmentMyProductBinding;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;

public class myProductFragment extends Fragment {

    private FragmentMyProductBinding binding;
    MyProductViewModel myProductViewModel;
    RecycleAdapterProduct recycleAdapterProduct;
    final int id = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        myProductViewModel = ViewModelProviders.of(this).get(MyProductViewModel.class);
        binding = FragmentMyProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i=new Intent(getActivity(), AddUpdateProductActivity.class);
            i.putExtra("name","add");
            startActivity(i);
            }
        });

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
                recycleAdapterProduct = new RecycleAdapterProduct(getActivity(), myProducts,"my");
                binding.myProductsRecycle.setAdapter(recycleAdapterProduct);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}