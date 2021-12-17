package com.MadeInMyHome.activity.myProduct;

import androidx.lifecycle.ViewModelProvider;
import com.MadeInMyHome.databinding.MyProductFragmentBinding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MadeInMyHome.R;
import com.MadeInMyHome.databinding.FragmentNotificationsBinding;

public class myProductFragment extends Fragment {

    private MyProductFragmentBinding binding;



    private MyProductViewModel mViewModel;

    public static myProductFragment newInstance() {
        return new myProductFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = MyProductFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}