package com.MadeInMyHome.activity.show_course;

import static com.MadeInMyHome.utilities.General.getSharedPreference;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.activity.user.UserProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityCourseBinding;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;

public class CourseActivity extends AppCompatActivity {

    ActivityCourseBinding binding;
    CourseViewModel courseViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;

    String id_user, id_course, token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        courseViewModel = ViewModelProviders.of(this).get(CourseViewModel.class);

        token = getSharedPreference(this, "token");
        id_course = getIntent().getExtras().getString("id");

        showUserProfileViewModel.getUserProfile(CourseActivity.this, token)
                .observe(CourseActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        id_user = user.getId();
                        courseViewModel.getEnroll(CourseActivity.this, id_user, id_course)
                                .observe(CourseActivity.this, new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        enrolled();
                                    }
                                });
                    }
                });

        binding.enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showUserProfileViewModel.getUserProfile(CourseActivity.this, token)
                        .observe(CourseActivity.this, new Observer<User>() {
                            @Override
                            public void onChanged(User user) {
                                courseViewModel.addEnroll(CourseActivity.this, user.getId(), id_course)
                                        .observe(CourseActivity.this, new Observer<String>() {
                                            @Override
                                            public void onChanged(String s) {
                                                enrolled();
                                            }
                                        });
                            }
                        });
            }
        });

        courseViewModel.getCourse(this, getIntent().getExtras().getString("id")).observe(this, new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                binding.name.setText(course.getName());
                binding.category.setText(course.getCategory());
                binding.description.setText(course.getDescription());
                binding.presenter.setText(course.getPresenter());
                new GlideImage(CourseActivity.this, constants.BASE_HOST + constants.IMAGE_COURSE + course.getImage(), binding.image);
            }
        });

    }

    public void enrolled() {
        binding.enroll.setText("Enrolled");
        binding.enroll.setClickable(false);
    }
}