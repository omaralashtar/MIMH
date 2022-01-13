package com.MadeInMyHome.activity.user.register;

import static com.MadeInMyHome.utilities.General.addToSharedPreference;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.R;
import com.MadeInMyHome.ViewModel.SignUpViewModel;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.user.LoginSignUpActivity;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.FragmentSignUpBinding;

import java.util.Calendar;

public class SignUpFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

    DatePickerDialog picker;
    String encodedImage;
    SignUpViewModel signUpViewModel;
    private FragmentSignUpBinding binding;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
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
            Toast.makeText(getActivity(), "firstName empty", Toast.LENGTH_SHORT).show();
            binding.fName.getEditText().setError("Please Enter firstName");
            binding.fName.getEditText().requestFocus();
            return;
        } else if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(getActivity(), "lastName empty", Toast.LENGTH_SHORT).show();
            binding.lName.getEditText().setError("Please Enter lastName");
            binding.lName.getEditText().requestFocus();
            return;
        }

        else if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "email empty", Toast.LENGTH_SHORT).show();
            binding.email.getEditText().setError("Please Enter Email");
            binding.email.getEditText().requestFocus();
            return;
        }
       else if (!email.matches(emailPattern)) {
            Toast.makeText(getActivity(), "valid email address", Toast.LENGTH_SHORT).show();
            binding.email.getEditText().setError("format in the email not correct");
            binding.email.getEditText().requestFocus();
            return;

        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "password empty", Toast.LENGTH_SHORT).show();
            binding.password.getEditText().setError("Please Enter Password");
            binding.password.getEditText().requestFocus();
            return;
        }
        else if (password.length() < 8) {
           Toast.makeText(getActivity(), "the password must be lingth 8 or more  ", Toast.LENGTH_SHORT).show();
         binding.password.getEditText().setError("Please Enter 8 character or more");
         binding.password.getEditText().requestFocus();
        }
        else if (TextUtils.isEmpty(date)) {
            Toast.makeText(getActivity(), "date  empty", Toast.LENGTH_SHORT).show();
            binding.date.getEditText().setError("Please Enter password");
            binding.date.getEditText().requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(gender)) {
            Toast.makeText(getActivity(), "select gender  empty", Toast.LENGTH_SHORT).show();
            binding.gender.getEditText().setError("Please Enter gender");
            binding.gender.getEditText().requestFocus();
            return;
        }
        else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getActivity(), "select phone  empty", Toast.LENGTH_SHORT).show();
            binding.phone.getEditText().setError("Please Enter phone");
            binding.phone.getEditText().requestFocus();
            return;
        }
        else if (!(phone.length() == 7) ) {
            Toast.makeText(getActivity(), "the phone must be lingth 7 number  ", Toast.LENGTH_SHORT).show();
            binding.phone.getEditText().setError("Please Enter 7 Number Phone ");
            binding.phone.getEditText().requestFocus();
        }
        else {
            progressDialog.setTitle("create Account");
            progressDialog.setMessage("create account Now");
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
            encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.image.getDrawable()).getBitmap());
            signUpViewModel.signUp(getActivity(),
                    email, password, firstName,
                    lastName, date, gender,
                    binding.startPhone.getEditText().getText() + "" +phone, encodedImage)
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