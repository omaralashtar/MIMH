package com.MadeInMyHome.activity.show_course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.MadeInMyHome.activity.video.VideoViewModel;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityCourseBinding;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.utilities.constants;

public class CourseActivity extends AppCompatActivity {

    ActivityCourseBinding binding;
    CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);

        courseViewModel.getCourse(this,getIntent().getExtras().getString("id")).observe(this, new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                binding.name.setText(course.getName());
                binding.category.setText(course.getCategory());
                binding.description.setText(course.getDescription());
                binding.presenter.setText(course.getPresenter());
                new GlideImage(CourseActivity.this, constants.BASE_HOST +constants.IMAGE_COURSE+ course.getImage(), binding.image);
            }
        });

    }
}