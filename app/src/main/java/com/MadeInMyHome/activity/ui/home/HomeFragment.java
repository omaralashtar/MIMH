package com.MadeInMyHome.activity.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.introduction.IntroductionActivity;
import com.MadeInMyHome.activity.productsByCategory.ProductByCategoryActivity;
import com.MadeInMyHome.activity.user.showProfileToUser.showProfileToUserActivity;
import com.MadeInMyHome.activity.welcom.WelcomeActivity;
import com.MadeInMyHome.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment  {

    private HomeViewModel homeViewModel;
private FragmentHomeBinding binding;
    private SliderAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });





        adapter = new SliderAdapter(getActivity());
        binding.imageSlider.setSliderAdapter(adapter);
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider.setIndicatorSelectedColor(Color.WHITE);
        binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.imageSlider.setScrollTimeInSec(3);
        binding.imageSlider.setAutoCycle(true);
        binding.imageSlider.startAutoCycle();


        List<SliderItem> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i < 5; i++) {
            SliderItem sliderItem = new SliderItem();

            if (i  == 0) {
                sliderItem.setDescription("Slider Item " + i);
                sliderItem.setImageUrl(R.drawable.ic_menu_camera);
            } else if(i==1) {
                sliderItem.setDescription("Slider Item " + i);
                sliderItem.setImageUrl(R.drawable.ic_menu_slideshow);
            }
            else if(i==2)
            {
                sliderItem.setDescription("Slider Item " + i);
                sliderItem.setImageUrl(R.drawable.splash_logo);
            }
            else
                {
                    sliderItem.setDescription("Slider Item " + i);
                    sliderItem.setImageUrl(R.drawable.profile);
                }
            sliderItemList.add(sliderItem);
        }
        adapter.renewItems(sliderItemList);










        binding.imageSlider.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + binding.imageSlider.getCurrentPagePosition());
            }
        });







//        binding.fabProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), showProfileToUser.class);
//                i.putExtra("id", "d3799829ab212a47a3b8b60734d243ab");
//                getActivity().startActivity(i);
//
//            }
//        });




        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}