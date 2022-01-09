package com.MadeInMyHome.activity.ui.category_course;

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

import com.MadeInMyHome.adapter.RecycleAdapterCategory;
import com.MadeInMyHome.databinding.FragmentCourseCategoryBinding;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;

public class categoryCourseFragment extends Fragment {

    categoryCourseViewModel categoryCourseViewModel;
    RecycleAdapterCategory CategoryCourseRecycleAdapter;
    private FragmentCourseCategoryBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        categoryCourseViewModel = ViewModelProviders.of(this).get(categoryCourseViewModel.class);
        binding = FragmentCourseCategoryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.courseProductRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

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
        categoryCourseViewModel.showCategoryCourse(getActivity()).observe(getActivity(), new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                CategoryCourseRecycleAdapter = new RecycleAdapterCategory(getActivity(), categories,"course");
                binding.courseProductRecycle.setAdapter(CategoryCourseRecycleAdapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}