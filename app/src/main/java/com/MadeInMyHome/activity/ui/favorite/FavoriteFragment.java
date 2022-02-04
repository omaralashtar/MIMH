package com.MadeInMyHome.activity.ui.favorite;

import static com.MadeInMyHome.utilities.General.getToken;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.activity.user.userProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.FragmentFavoriteBinding;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.User;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    RecycleAdapterProduct recycleAdapterProduct;

    FavoriteViewModel favoriteViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;

    String next = "0";

    private FragmentFavoriteBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.favoriteRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        setAdapter();

        return root;
    }

    public void setAdapter() {
        showUserProfileViewModel.getUserProfile(getActivity(), getToken(getActivity())).observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                favoriteViewModel.getProducts(getActivity(), user.getId(), next).observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
                    @Override
                    public void onChanged(ArrayList<Product> products) {
                        recycleAdapterProduct = new RecycleAdapterProduct(getActivity(), products, "product",showUserProfileViewModel);
                        binding.favoriteRecycle.setAdapter(recycleAdapterProduct);
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