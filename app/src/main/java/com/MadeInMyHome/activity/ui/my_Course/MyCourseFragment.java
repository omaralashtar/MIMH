package com.MadeInMyHome.activity.ui.my_Course;

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
import com.MadeInMyHome.adapter.RecycleAdapterCourse;
import com.MadeInMyHome.databinding.FragmentMyCoursesBinding;
import com.MadeInMyHome.databinding.FramentProductCategoryBinding;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.model.Course;

import java.util.ArrayList;

public class MyCourseFragment extends Fragment {

    MyCourseViewModel myCourseViewModel;
    RecycleAdapterCourse courserRecycleAdapter;
    private FragmentMyCoursesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        myCourseViewModel = ViewModelProviders.of(this).get(MyCourseViewModel.class);
        binding = FragmentMyCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.coursesRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        setAdapter("1","0");

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapter("1","0");
            }
        });

        return root;
    }

    public void setAdapter(String id,String next) {
        myCourseViewModel.showMyCourse(getActivity(),id,next).observe(getActivity(), new Observer<ArrayList<Course>>() {
            @Override
            public void onChanged(ArrayList<Course> courses) {
                courserRecycleAdapter = new RecycleAdapterCourse(getActivity(), courses,"myCourse");
                binding.coursesRecycle.setAdapter(courserRecycleAdapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}