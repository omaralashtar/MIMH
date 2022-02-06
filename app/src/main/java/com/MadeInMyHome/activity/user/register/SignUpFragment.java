package com.MadeInMyHome.activity.user.register;

import static com.MadeInMyHome.utilities.General.addToSharedPreference;
import static com.MadeInMyHome.utilities.General.emailMessage;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.R;
import com.MadeInMyHome.ViewModel.SendMessageViewModel;
import com.MadeInMyHome.ViewModel.SignUpViewModel;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.user.LoginSignUpActivity;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.FragmentSignUpBinding;

import java.util.Calendar;
import java.util.Random;

public class SignUpFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

    DatePickerDialog picker;
    String encodedImage;
    EditText code;
    SignUpViewModel signUpViewModel;
    SendMessageViewModel sendMessageViewModel;
    private int codeNum;
    private FragmentSignUpBinding binding;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        sendMessageViewModel =new ViewModelProvider(this).get(SendMessageViewModel.class);
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        progressDialog = new ProgressDialog(getActivity());

        ArrayAdapter<String> genderAdapter =
                new ArrayAdapter<String>(getActivity(), R.layout.dropdown_menu_popup_item, getResources().getStringArray(R.array.genderList));

        binding.genderDropdown.setAdapter(genderAdapter);

        ArrayAdapter<String> phoneAdapter =
                new ArrayAdapter<String>(getActivity(), R.layout.dropdown_menu_popup_item, getResources().getStringArray(R.array.phoneList));

        binding.startPhoneDropdown.setAdapter(phoneAdapter);

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginSignUpActivity.binding.viewPager.setCurrentItem(0);
            }
        });

        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PickImage(getActivity(), binding.image);
            }
        });

        binding.datetext.setOnClickListener(this);
        binding.datetext.setOnFocusChangeListener(this);

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        return root;
    }


    private void signUp() {

        String firstName = binding.fName.getEditText().getText().toString();
        String lastName = binding.lName.getEditText().getText().toString();
        String email = binding.email.getEditText().getText().toString().trim();
        String password = binding.password.getEditText().getText().toString();
        String date = binding.date.getEditText().getText().toString();
        String gender = binding.gender.getEditText().getText().toString();
        String phone = binding.phone.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


        if (TextUtils.isEmpty(firstName)) {
            binding.fName.getEditText().setError("Please Enter firstName");
            binding.fName.getEditText().requestFocus();
            return;
        } else if (TextUtils.isEmpty(lastName)) {
            binding.lName.getEditText().setError("Please Enter lastName");
            binding.lName.getEditText().requestFocus();
            return;
        } else if (TextUtils.isEmpty(email)) {
            binding.email.getEditText().setError("Please Enter Email");
            binding.email.getEditText().requestFocus();
            return;
        } else if (!email.matches(emailPattern)) {
            binding.email.getEditText().setError("format in the email not correct");
            binding.email.getEditText().requestFocus();
            return;

        } else if (TextUtils.isEmpty(password)) {
            binding.password.getEditText().setError("Please Enter Password");
            binding.password.getEditText().requestFocus();
            return;
        } else if (password.length() < 8) {
            binding.password.getEditText().setError("Please Enter 8 character or more");
            binding.password.getEditText().requestFocus();
        } else if (TextUtils.isEmpty(date)) {
            binding.date.getEditText().setError("Please Enter password");
            binding.date.getEditText().requestFocus();
            return;
        } else if (TextUtils.isEmpty(gender)) {
            binding.gender.getEditText().setError("Please Enter gender");
            binding.gender.getEditText().requestFocus();
            return;
        } else if (TextUtils.isEmpty(phone)) {
            binding.phone.getEditText().setError("Please Enter phone");
            binding.phone.getEditText().requestFocus();
            return;
        } else if (!(phone.length() == 7)) {
            binding.phone.getEditText().setError("Please Enter 7 Number Phone ");
            binding.phone.getEditText().requestFocus();
        } else {
            progressDialog.setTitle("create Account");
            progressDialog.setMessage("create account Now");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
            encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.image.getDrawable()).getBitmap());
            signUpViewModel.signUp(getActivity(),
                    email, password, firstName,
                    lastName, date, gender,
                    binding.startPhone.getEditText().getText() + "" + phone, encodedImage)
                    .observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (!s.equals("0")) {
                                Intent i = new Intent(getActivity(), MainActivity.class);
                                addToSharedPreference(getActivity(), "token", s);
                                startActivity(i);
                                getActivity().finish();
                            } else {
                                progressDialog.dismiss();
                                codeNum = new Random().nextInt(899999) + 100000;;

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
                    });
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        setDate();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b)
            setDate();
    }

    public void setDate() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                binding.date.getEditText().setText(year + "-" + month + 1 + "-" + day);
            }
        }, year, month, day);
        picker.show();
    }
}