package com.MadeInMyHome.activity.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.MadeInMyHome.activity.MainActivityViewModel;
import com.MadeInMyHome.R;
import com.MadeInMyHome.utilities.EqualSpacingItemDecoration;
import com.MadeInMyHome.utilities.Utility;

public class CategoryProductActivity extends Fragment {

    RecyclerView recHorizental;
    private CategoryProductViewModel mViewModel;

    public static CategoryProductActivity newInstance() {
        return new CategoryProductActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.category_product_fragment, container, false);
        recHorizental = v.findViewById(R.id.recHori);

        int spacing = 10;
        EqualSpacingItemDecoration equalSpacing = new EqualSpacingItemDecoration(spacing);
        recHorizental.addItemDecoration(equalSpacing);

        int mNoOfColumns = Utility.calculateNoOfColumns(getActivity());


        // recHorizental.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        recHorizental.setLayoutManager(new GridLayoutManager(getActivity(), mNoOfColumns, LinearLayoutManager.HORIZONTAL, true));

        MainActivityViewModel mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.showMealcategoryhorizental("", getActivity(), recHorizental).observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        recHorizental.getRecycledViewPool().setMaxRecycledViews(0, 0);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryProductViewModel.class);
        // TODO: Use the ViewModel
    }

}