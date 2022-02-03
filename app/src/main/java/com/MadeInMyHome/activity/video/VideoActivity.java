package com.MadeInMyHome.activity.video;

import static com.MadeInMyHome.utilities.General.getToken;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.show_course.CourseViewModel;
import com.MadeInMyHome.activity.user.UserProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterVideo;
import com.MadeInMyHome.databinding.ActivityVideoBinding;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.model.Video;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    ActivityVideoBinding binding;
    VideoViewModel videoViewModel;
    CourseViewModel courseViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;
    RecycleAdapterVideo recycleAdapterVideo;
    EditText message;
    String id_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        videoViewModel = new ViewModelProvider(this).get(VideoViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);

        id_course=getIntent().getExtras().getString("id");

        binding.videoRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        videoViewModel.getVideos(this,id_course ).observe(this, new Observer<ArrayList<Video>>() {
            @Override
            public void onChanged(ArrayList<Video> videos) {
                recycleAdapterVideo = new RecycleAdapterVideo(VideoActivity.this, videos, binding.webView);
                binding.videoRecycler.setAdapter(recycleAdapterVideo);
            }
        });

        courseViewModel.getCourse(this,id_course).observe(this, new Observer<Course>() {
            @Override
            public void onChanged(Course course) {
                binding.name.setText(course.getName());
                binding.presenter.setText(course.getPresenter());
            }
        });

        binding.report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View promptUserView = getLayoutInflater().inflate(R.layout.dialog_report_video, null);
                message = (EditText) promptUserView.findViewById(R.id.message);

                AlertDialog alertDialog = new AlertDialog.Builder(VideoActivity.this)
                        .setCancelable(false)
                        .setView(promptUserView)
                        .setTitle("Report Video")
                        .setPositiveButton("report", null)
                        .setNegativeButton("cancel", null)
                        .create();

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!message.getText().equals("")) {
                                    message.setError(null);
                                    showUserProfileViewModel.getUserProfile(VideoActivity.this,getToken(VideoActivity.this))
                                            .observe(VideoActivity.this, new Observer<User>() {
                                                @Override
                                                public void onChanged(User user) {
                                                    videoViewModel.reportVideo(VideoActivity.this, user.getId(),recycleAdapterVideo.selectVideo().getId(), message.getText().toString())
                                                            .observe(VideoActivity.this, new Observer<String>() {
                                                                @Override
                                                                public void onChanged(String s) {
                                                                    alertDialog.dismiss();
                                                                }
                                                            });
                                                }
                                            });
                                } else {
                                    message.setError("empty message");
                                }
                            }
                        });
                    }
                });

                alertDialog.show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}