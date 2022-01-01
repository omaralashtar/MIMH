package com.MadeInMyHome.activity.user.login;

import static com.MadeInMyHome.utilities.General.addToSharedPreference;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.ViewModel.LoginViewModel;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.user.LoginSignUpActivity;
import com.MadeInMyHome.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSignUpActivity.binding.viewPager.setCurrentItem(1);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginViewModel.login(getActivity(), binding.email.getEditText(), binding.password.getEditText())
                        .observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Intent i = new Intent(getActivity(), MainActivity.class);
                                addToSharedPreference(getActivity(), "id", s);
                                startActivity(i);
                                getActivity().finish();
                            }
                        });
            }
        });

        return root;
    }






}