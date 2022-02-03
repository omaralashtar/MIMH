package com.MadeInMyHome.activity.ui.my_products;

import static com.MadeInMyHome.utilities.General.getToken;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.MadeInMyHome.activity.add_update_product.AddUpdateProductActivity;
import com.MadeInMyHome.activity.user.UserProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.FragmentMyProductBinding;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.User;

import java.util.ArrayList;

public class myProductFragment extends Fragment {

    MyProductViewModel myProductViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;

    RecycleAdapterProduct myProductsAdapter;
    private FragmentMyProductBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        myProductViewModel = new ViewModelProvider(this).get(MyProductViewModel.class);
        showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);

        binding = FragmentMyProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddUpdateProductActivity.class);
                i.putExtra("name", "add");
                startActivity(i);
            }
        });

        binding.myProductsRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));

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
        showUserProfileViewModel.getUserProfile(getActivity(), getToken(getActivity())).observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                myProductViewModel.getMyProducts(getActivity(), user.getId()).observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
                    @Override
                    public void onChanged(ArrayList<Product> myProducts) {
                        myProductsAdapter = new RecycleAdapterProduct(getActivity(), myProducts, "my");
                        binding.myProductsRecycle.setAdapter(myProductsAdapter);
                    }
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}