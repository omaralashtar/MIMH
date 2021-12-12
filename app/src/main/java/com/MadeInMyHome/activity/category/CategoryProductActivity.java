package com.MadeInMyHome.activity.category;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MadeInMyHome.MainActivityViewModel;
import com.MadeInMyHome.R;
import com.MadeInMyHome.utilities.EqualSpacingItemDecoration;
import com.MadeInMyHome.utilities.Utility;

public class CategoryProduct extends Fragment {

    private ShowCategoryProductViewModel mViewModel;
    RecyclerView recHorizental;
    public static CategoryProduct newInstance() {
        return new CategoryProduct();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.show_category_product_fragment, container, false);
        recHorizental=v.findViewById(R.id.recHori);

        int spacing = 10;
        EqualSpacingItemDecoration equalSpacing = new EqualSpacingItemDecoration(spacing);
        recHorizental.addItemDecoration(equalSpacing);

        int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());


        // recHorizental.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        recHorizental.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns, LinearLayoutManager.HORIZONTAL,true));
       MainActivityViewModel vm = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        vm.show_Meal_category_horizental("", getActivity(), recHorizental).observe(getActivity(), new Observer<String>() {@Override
            public void onChanged(String s) {

           }
       });


        recHorizental.getRecycledViewPool().setMaxRecycledViews(0, 0);





        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShowCategoryProductViewModel.class);
        // TODO: Use the ViewModel
    }

}