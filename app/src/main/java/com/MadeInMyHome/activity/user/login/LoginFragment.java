package com.MadeInMyHome.activity.user.login;

import static com.MadeInMyHome.utilities.General.addToSharedPreference;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressDialog = new ProgressDialog(getActivity());
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSignUpActivity.binding.viewPager.setCurrentItem(1);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });

        return root;
    }


    private void logIn() {


        String email = binding.email.getEditText().getText().toString().trim();
        String password = binding.password.getEditText().getText().toString();


        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "email empty", Toast.LENGTH_SHORT).show();
            binding.email.getEditText().setError("Please Enter Email");
            binding.email.getEditText().requestFocus();
            return;
        }
       /* else if (!email.matches(emailPattern)) {
            Toast.makeText(getActivity(), "valid email address", Toast.LENGTH_SHORT).show();
            binding.email.getEditText().setError("format in the email not correct");
            binding.email.getEditText().requestFocus();
            return;

        }
        else if (password.length() < 8) {
           Toast.makeText(getActivity(), "the password must be lingth 8 or more  ", Toast.LENGTH_SHORT).show();
         binding.password.getEditText().setError("Please Enter 8 character or more");
         binding.password.getEditText().requestFocus();
        }*/

        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "password empty", Toast.LENGTH_SHORT).show();
            binding.password.getEditText().setError("Please Enter Password");
            binding.password.getEditText().requestFocus();
            return;
        } else {
            progressDialog.setTitle("Login Account");
            progressDialog.setMessage("email or password not found");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
            loginViewModel.login(getActivity(), email, password)
                    .observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            addToSharedPreference(getActivity(), "token", s);
                            startActivity(i);
                            getActivity().finish();
                        }
                    });
        }


    }


}