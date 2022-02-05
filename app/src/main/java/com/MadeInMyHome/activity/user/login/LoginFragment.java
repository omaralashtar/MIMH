package com.MadeInMyHome.activity.user.login;

import static com.MadeInMyHome.utilities.General.addToSharedPreference;
import static com.MadeInMyHome.utilities.General.emailMessage;
import static com.MadeInMyHome.utilities.General.emailMessageForgetPassword;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.MadeInMyHome.R;
import com.MadeInMyHome.ViewModel.LoginViewModel;
import com.MadeInMyHome.ViewModel.SendMessageViewModel;
import com.MadeInMyHome.ViewModel.SignUpViewModel;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.user.LoginSignUpActivity;
import com.MadeInMyHome.databinding.FragmentLoginBinding;

import java.util.Random;

public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;
    SignUpViewModel signUpViewModel;
    SendMessageViewModel sendMessageViewModel;
    EditText code,emailF,newPassword;
    private FragmentLoginBinding binding;
    private ProgressDialog progressDialog;
    private int codeNum,step=0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        sendMessageViewModel =new ViewModelProvider(this).get(SendMessageViewModel.class);

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

        binding.newPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgetPassword();
            }
        });

        return root;
    }

    public void forgetPassword(){

        View promptUserView = getLayoutInflater().inflate(R.layout.dialog_reset_password, null);
        emailF = (EditText) promptUserView.findViewById(R.id.email);
        code = (EditText) promptUserView.findViewById(R.id.code);
        newPassword = (EditText) promptUserView.findViewById(R.id.newPassword);

        code.setFocusable(false);
        newPassword.setFocusable(false);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .setView(promptUserView)
                .setTitle("Forget password")
                .setPositiveButton("next", null)
                .setNegativeButton("cancel", null)
                .create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(step==0) {
                            if (TextUtils.isEmpty(emailF.getText())) {
                                emailF.setError("Please Enter Email");
                                emailF.requestFocus();
                                return;
                            } else if (!emailF.getText().toString().matches(emailPattern)) {
                                binding.email.getEditText().setError("format in the email not correct");
                                binding.email.getEditText().requestFocus();
                                return;
                            } else {
                                codeNum = new Random().nextInt(899999) + 100000;

                                sendMessageViewModel.sendMessage(getActivity(), emailF.getText().toString(), emailMessageForgetPassword(emailF.getText().toString(), String.valueOf(codeNum)))
                                        .observe(getActivity(), new Observer<String>() {
                                            @Override
                                            public void onChanged(String s) {

                                            }
                                        });
                                emailF.setFocusable(false);
                                step++;
                                code.setFocusable(true);
                                code.setFocusableInTouchMode(true);}
                        }else if(step==1){
                            if (code.getText().toString().equals(codeNum + "")) {
                                code.setError(null);
                                code.setFocusable(false);
                                step++;
                                newPassword.setFocusable(true);
                                newPassword.setFocusableInTouchMode(true);
                            } else {
                                code.setError("not correct");
                            }
                        }else if(step==2){
                        loginViewModel.resetPassword(getActivity(),emailF.getText().toString(),newPassword.getText().toString())
                                .observe(getActivity(), new Observer<String>() {
                                    @Override
                                    public void onChanged(String s) {
                                        Toast.makeText(getActivity(), "password changed", Toast.LENGTH_SHORT).show();
                                        alertDialog.dismiss();
                                    }
                                });
                        }
                    }
                });
            }
        });

        alertDialog.show();

    }

    private void logIn() {


        String email = binding.email.getEditText().getText().toString().trim();
        String password = binding.password.getEditText().getText().toString();

        if (TextUtils.isEmpty(email)) {
            binding.email.getEditText().setError("Please Enter Email");
            binding.email.getEditText().requestFocus();
            return;
        }
       /* else if (!email.matches(emailPattern)) {
            binding.email.getEditText().setError("format in the email not correct");
            binding.email.getEditText().requestFocus();
            return;

        }
        else if (password.length() < 8) {
         binding.password.getEditText().setError("Please Enter 8 character or more");
         binding.password.getEditText().requestFocus();
        }*/

        else if (TextUtils.isEmpty(password)) {
            binding.password.getEditText().setError("Please Enter Password");
            binding.password.getEditText().requestFocus();
            return;
        } else {
            progressDialog.setTitle("Login Account");
            progressDialog.setMessage("email or password not found");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            loginViewModel.login(getActivity(), email, password)
                    .observe(getActivity(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (s.equals("1")) {
                                Intent i = new Intent(getActivity(), MainActivity.class);
                                addToSharedPreference(getActivity(), "token", s);
                                startActivity(i);
                                getActivity().finish();
                            } else {
                                if (!s.equals("0")) {
                                    Intent i = new Intent(getActivity(), MainActivity.class);
                                    addToSharedPreference(getActivity(), "token", s);
                                    startActivity(i);
                                    getActivity().finish();
                                } else {
                                    progressDialog.dismiss();
                                    codeNum = new Random().nextInt(899999) + 100000;

                                    sendMessageViewModel.sendMessage(getActivity(),email,emailMessage(email,String.valueOf(codeNum)))
                                            .observe(getActivity(), new Observer<String>() {
                                                @Override
                                                public void onChanged(String s) {

                                                }
                                            });

                                    View promptUserView = getLayoutInflater().inflate(R.layout.dialog_active_email, null);
                                    code = (EditText) promptUserView.findViewById(R.id.code);
                                    TextView message = (TextView)promptUserView.findViewById(R.id.text);
                                    message.setText(message.getText()+" "+email+" with code \nplease put it here");

                                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                                            .setCancelable(false)
                                            .setView(promptUserView)
                                            .setTitle("validate your email")
                                            .setPositiveButton("activate", null)
                                            .setNegativeButton("cancel", null)
                                            .create();

                                    alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                                        @Override
                                        public void onShow(DialogInterface dialogInterface) {
                                            alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    if (code.getText().toString().equals(codeNum + "")) {
                                                        code.setError(null);
                                                        signUpViewModel.active(getActivity(), email).observe(getActivity(), new Observer<String>() {
                                                            @Override
                                                            public void onChanged(String s) {
                                                                Intent i = new Intent(getActivity(), MainActivity.class);
                                                                addToSharedPreference(getActivity(), "token", s);
                                                                startActivity(i);
                                                                getActivity().finish();
                                                            }
                                                        });
                                                    } else {
                                                        code.setError("not correct");
                                                    }
                                                }
                                            });
                                        }
                                    });

                                    alertDialog.show();

                                }
                            }
                        }
                    });
        }
    }
}