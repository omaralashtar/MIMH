package com.MadeInMyHome.activity.LoginSignUp;

import android.app.DatePickerDialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.FragmentSignUpBinding;

import java.util.Calendar;

public class SignUpFragment extends Fragment {

    DatePickerDialog picker;
    String encodedImage;
    PickImage pickImage;
    SignUpViewModel signUpViewModel;
    private FragmentSignUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ArrayAdapter<String> genderAdapter =
                new ArrayAdapter<String>(getActivity(), R.layout.dropdown_menu_popup_item, getResources().getStringArray(R.array.genderList));

        binding.genderDropdown.setAdapter(genderAdapter);

        ArrayAdapter<String> phoneAdapter =
                new ArrayAdapter<String>(getActivity(), R.layout.dropdown_menu_popup_item, getResources().getStringArray(R.array.phoneList));

        binding.startPhoneDropdown.setAdapter(phoneAdapter);

        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage = new PickImage(getActivity(), binding.image);
                signUpViewModel.signUp(getActivity(),
                        binding.email.getEditText(), binding.password.getEditText(), binding.fName.getEditText(),
                        binding.lName.getEditText(), binding.date.getEditText(), binding.gender.getEditText(),
                        binding.startPhone.getEditText() + "" + binding.phone.getEditText(), encodedImage)
                        .observe(getViewLifecycleOwner(), new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        binding.date.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    final Calendar cldr = Calendar.getInstance();
                    int day = cldr.get(Calendar.DAY_OF_MONTH);
                    int month = cldr.get(Calendar.MONTH);
                    int year = cldr.get(Calendar.YEAR);
                    picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            binding.date.getEditText().setText(year + "/" + month + "/" + day);
                        }
                    }, year, month, day);
                    picker.show();
                }
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