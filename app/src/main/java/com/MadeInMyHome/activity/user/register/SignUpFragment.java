package com.MadeInMyHome.activity.user.register;

import static com.MadeInMyHome.utilities.General.addToSharedPreference;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.R;
import com.MadeInMyHome.ViewModel.SignUpViewModel;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.FragmentSignUpBinding;

import java.util.Calendar;

public class SignUpFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

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

            }
        });
        binding.datetext.setOnClickListener(this);
        binding.datetext.setOnFocusChangeListener(this);

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.image.getDrawable()).getBitmap());
                signUpViewModel.signUp(getActivity(),
                        binding.email.getEditText(), binding.password.getEditText(), binding.fName.getEditText(),
                        binding.lName.getEditText(), binding.date.getEditText(), binding.gender.getEditText(),
                        binding.startPhone.getEditText().getText() + "" + binding.phone.getEditText().getText(), encodedImage)
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

    public void setDate(){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                binding.date.getEditText().setText(year + "-" + month + "-" + day);
            }
        }, year, month, day);
        picker.show();
    }
}