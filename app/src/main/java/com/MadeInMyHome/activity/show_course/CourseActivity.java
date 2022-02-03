package com.MadeInMyHome.activity.show_course;

import static com.MadeInMyHome.utilities.General.getToken;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.MadeInMyHome.activity.user.userProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityCourseBinding;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;

public class CourseActivity extends AppCompatActivity {

    ActivityCourseBinding binding;
    CourseViewModel courseViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;

    String id_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);

        id_course = getIntent().getExtras().getString("id");

        showUserProfileViewModel.getUserProfile(CourseActivity.this, getToken(CourseActivity.this))
                .observe(CourseActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        courseViewModel.getEnroll(CourseActivity.this, user.getId(), id_course)
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
                showUserProfileViewModel.getUserProfile(CourseActivity.this, getToken(CourseActivity.this))
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}