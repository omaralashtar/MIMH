package com.MadeInMyHome.activity.ui.my_Course;

import static com.MadeInMyHome.utilities.General.getSharedPreference;
import static com.MadeInMyHome.utilities.General.getToken;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.MadeInMyHome.activity.user.UserProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterCourse;
import com.MadeInMyHome.databinding.FragmentMyCoursesBinding;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.model.User;

import java.util.ArrayList;

public class MyCourseFragment extends Fragment {

    MyCourseViewModel myCourseViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;

    RecycleAdapterCourse courserRecycleAdapter;
    private FragmentMyCoursesBinding binding;

    String next="0";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);
        myCourseViewModel = new ViewModelProvider(this).get(MyCourseViewModel.class);

        binding = FragmentMyCoursesBinding.inflate(inflater, container, false);
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

    public void setAdapter() {
        showUserProfileViewModel.getUserProfile(getActivity(),getToken(getActivity())).observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                myCourseViewModel.showMyCourse(getActivity(), user.getId(), next).observe(getActivity(), new Observer<ArrayList<Course>>() {
                    @Override
                    public void onChanged(ArrayList<Course> courses) {
                        courserRecycleAdapter = new RecycleAdapterCourse(getActivity(), courses,"myCourse");
                        binding.coursesRecycle.setAdapter(courserRecycleAdapter);
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