package com.MadeInMyHome.activity.ui.category_course;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MadeInMyHome.R;
import com.MadeInMyHome.adapter.RecycleAdapterCategory;
import com.MadeInMyHome.databinding.FragmentCategoryCourseHorizontalBinding;
import com.MadeInMyHome.databinding.FragmentCourseCategoryBinding;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.utilities.EqualSpacingItemDecoration;

import java.util.ArrayList;


public class category_course_horizental extends Fragment {

    categoryCourseViewModel categoryCourseViewModel;
    RecycleAdapterCategory CategoryCourseRecycleAdapter;
    private FragmentCategoryCourseHorizontalBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        categoryCourseViewModel = ViewModelProviders.of(this).get(categoryCourseViewModel.class);
        binding = FragmentCategoryCourseHorizontalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        int spacing = 10;
        EqualSpacingItemDecoration equalSpacing = new EqualSpacingItemDecoration(spacing);
        binding.recycle.addItemDecoration(equalSpacing);

        binding.recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        setAdapter();


        return root;
    }

    public void setAdapter() {
        categoryCourseViewModel.showCategoryCourse(getActivity()).observe(getActivity(), new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                CategoryCourseRecycleAdapter = new RecycleAdapterCategory(getActivity(), categories,"course");
                binding.recycle.setAdapter(CategoryCourseRecycleAdapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}