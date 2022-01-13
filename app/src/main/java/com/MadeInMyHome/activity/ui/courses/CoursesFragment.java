package com.MadeInMyHome.activity.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.MadeInMyHome.adapter.RecycleAdapterCourse;
import com.MadeInMyHome.databinding.FragmentCoursesBinding;
import com.MadeInMyHome.model.Course;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    RecycleAdapterCourse recycleAdapterCourse;
    CoursesViewModel coursesViewModel;
    private FragmentCoursesBinding binding;

    int next=0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        coursesViewModel= new ViewModelProvider(this).get(CoursesViewModel.class);
        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.coursesRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        setAdapter();

        binding.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setAdapter();
            }
        });

        return root;
    }

    public void setAdapter(){
        coursesViewModel.getCourses(getActivity(),String.valueOf(next)).observe(getViewLifecycleOwner(), new Observer<ArrayList<Course>>() {
            @Override
            public void onChanged(ArrayList<Course> courses) {
                recycleAdapterCourse =new RecycleAdapterCourse(getActivity(),courses,"course");
                binding.coursesRecycle.setAdapter(recycleAdapterCourse);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}