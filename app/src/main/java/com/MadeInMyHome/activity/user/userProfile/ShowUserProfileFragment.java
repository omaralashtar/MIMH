package com.MadeInMyHome.activity.user.userProfile;

import static com.MadeInMyHome.utilities.General.addToSharedPreference;
import static com.MadeInMyHome.utilities.General.emailMessage;
import static com.MadeInMyHome.utilities.General.getToken;

import android.app.DatePickerDialog;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.FragmentShowProfileUserBinding;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.util.Calendar;
import java.util.Random;

public class ShowUserProfileFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

    DatePickerDialog picker;
    String encodedImage;

    private ShowUserProfileViewModel showUserProfileViewModel;
    private FragmentShowProfileUserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        showUserProfileViewModel =
                new ViewModelProvider(this).get(ShowUserProfileViewModel.class);

        binding = FragmentShowProfileUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.EmailUser.setEnabled(false);
        binding.PhoneUser.setEnabled(false);
        binding.genderUser.setEnabled(false);
        binding.dateUser.setEnabled(false);
        binding.dateUser.setOnClickListener(this);
        binding.dateUser.setOnFocusChangeListener(this);


        ArrayAdapter<String> jordanAdapter =
                new ArrayAdapter<String>(getActivity(),
                        R.layout.dropdown_menu_popup_item,
                        getResources().
                                getStringArray(R.array.locationList));

        binding.LocationDropdown.setAdapter(jordanAdapter);

        binding.imgUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImageDialog.build(new PickSetup())
                        .setOnPickResult(new IPickResult() {
                            @Override
                            public void onPickResult(PickResult r) {
                                encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.imgUserProfile.getDrawable()).getBitmap());
                                showUserProfileViewModel.updateImageUser(getActivity(),
                                        getToken(getActivity()), encodedImage)
                                        .observe(getViewLifecycleOwner(), new Observer<String>() {
                                            @Override
                                            public void onChanged(String s) {
                                                binding.imgUserProfile.setImageBitmap(r.getBitmap());
                                            }
                                        });
                            }
                        }).show(getActivity());
            }
        });

        showUserProfileViewModel.getUserProfile(getActivity(), getToken(getActivity())).observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.FirstName.getEditText().setText(user.getF_name());
                binding.LastName.getEditText().setText(user.getL_name());
                binding.EmailUser.getEditText().setText(user.getEmail());
                binding.DescriptionUser.getEditText().setText(user.getDescription());
                binding.genderUser.getEditText().setText(user.getGender());
                binding.PhoneUser.getEditText().setText(user.getPhone());
                binding.LocationUser.getEditText().setText(user.getLocation());
                binding.dateUser.getEditText().setText(user.getDate());
                new GlideImage(getActivity(), constants.BASE_HOST + constants.IMAGE_USER + user.getImage(), binding.imgUserProfile);
            }
        });

        binding.btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            updateProfile();
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

    public void setDate() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                binding.dateUser.getEditText().setText(year + "-" + month+1 + "-" + day);
            }
        }, year, month, day);
        picker.show();
    }


    private void updateProfile() {

        String firstName = binding.FirstName.getEditText().getText().toString();
        String lastName = binding.LastName.getEditText().getText().toString();

        if (TextUtils.isEmpty(firstName)) {
            Toast.makeText(getActivity(), "firstName empty", Toast.LENGTH_SHORT).show();
            binding.FirstName.getEditText().setError("Please Enter firstName");
            binding.FirstName.getEditText().requestFocus();
            return;
        } else if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(getActivity(), "lastName empty", Toast.LENGTH_SHORT).show();
            binding.LastName.getEditText().setError("Please Enter lastName");
            binding.LastName.getEditText().requestFocus();
            return;
        } else {

            encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.imgUserProfile.getDrawable()).getBitmap());
            showUserProfileViewModel.updateUser(getActivity(),
                    getToken(getActivity()),
                    binding.FirstName.getEditText(),
                    binding.LastName.getEditText(),
                    binding.DescriptionUser.getEditText(),
                    binding.dateUser.getEditText(),
                    binding.LocationUser.getEditText())
                    .observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            getActivity().finish();
                        }
                    });
        }
    }






}