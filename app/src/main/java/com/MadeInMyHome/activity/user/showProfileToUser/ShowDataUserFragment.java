package com.MadeInMyHome.activity.user.showProfileToUser;

import static com.MadeInMyHome.utilities.General.getToken;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.ui.my_products.MyProductViewModel;
import com.MadeInMyHome.activity.user.userProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.FragmentProfileToUserBinding;
import com.MadeInMyHome.databinding.FragmentShowProfileUserBinding;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;


public class ShowDataUserFragment extends Fragment {
    ShowUserProfileViewModel showUserProfileViewModel;
    private FragmentProfileToUserBinding binding;
    MyProductViewModel myProductViewModel;
    RecycleAdapterProduct recycleAdapterProduct;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileToUserBinding.inflate(inflater, container, false);
        showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);
        myProductViewModel= ViewModelProviders.of(this).get(MyProductViewModel.class);

        View root = binding.getRoot();




        binding.myProductsRecycle.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        showUserProfileViewModel.getUserProfile(getActivity(), getActivity().getIntent().getExtras().getString("id")).observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.name.setText(user.getF_name() + "" + user.getL_name());
                binding.description.setText(user.getDescription());
                binding.gender.setText(user.getGender());
                //binding.LocationUser.getEditText().setText(user.getLocation());
                new GlideImage(getActivity(), constants.BASE_HOST + constants.IMAGE_USER + user.getImage(), binding.image);

            }
        });

        myProductViewModel.getMyProducts(getActivity(), getActivity().getIntent().getExtras().getString("id")).observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> myProducts) {
                recycleAdapterProduct = new RecycleAdapterProduct(getActivity(), myProducts,"product",showUserProfileViewModel);
                binding.myProductsRecycle.setAdapter(recycleAdapterProduct);
            }
        });








binding.cart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        NavHostFragment.findNavController(ShowDataUserFragment.this).
                navigate(R.id.action_FirstFragment_to_SecondFragment);
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