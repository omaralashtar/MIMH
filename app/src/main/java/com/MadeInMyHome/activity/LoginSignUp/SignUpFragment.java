package com.MadeInMyHome.activity.LoginSignUp;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.ViewModel.SignUpViewModel;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    String encodedImage;
    PickImage pickImage;
    private FragmentSignUpBinding binding;
    SignUpViewModel signUpViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage = new PickImage(getActivity(), binding.image);
                signUpViewModel.signUp(getActivity(),
                        binding.email.getEditText(),binding.password.getEditText(), binding.fName.getEditText(),
                        binding.lName.getEditText(),binding.date.getEditText(), binding.gender.getEditText(),
                        binding.startPhone.getEditText()+""+binding.phone.getEditText(),encodedImage)
                        .observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.image.getDrawable()).getBitmap());
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}