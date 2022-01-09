package com.MadeInMyHome.activity.user.showProfileToUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.ui.home.HomeViewModel;
import com.MadeInMyHome.activity.user.UserProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.FragmentFirstBinding;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;


public class FirstFragment extends Fragment {
    ShowUserProfileViewModel showUserProfileViewModel;
    private FragmentFirstBinding binding;
    String id="033e852c5da3c055f0a3937bcd9112f7";
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        showUserProfileViewModel=new ViewModelProvider(this).get(ShowUserProfileViewModel.class);



        showUserProfileViewModel.getUserProfile(getActivity(),"033e852c5da3c055f0a3937bcd9112f7").observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.name.setText(user.getF_name()+""+user.getL_name());
                //binding.LastName.getEditText().setText(user.getF_name());
                //binding.EmailUser.getEditText().setText(user.getEmail());
                binding.description.setText(user.getDescription());
               // binding.genderUser.getEditText().setText(user.getGender());
               // binding.PhoneUser.getEditText().setText(user.getPhone());
                //binding.LocationUser.getEditText().setText(user.getLocation());
                //binding.dateUser.getEditText().setText(user.getDate());
               // new GlideImage(getActivity(), constants.BASE_HOST + constants.IMAGE_USER + user.getImage(), binding.image);

            }
        });
















        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      /*  binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}