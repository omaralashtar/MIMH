package com.MadeInMyHome.activity.user.UserProfile;

import static com.MadeInMyHome.utilities.General.addToSharedPreference;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.FragmentShowProfileUserBinding;
import com.MadeInMyHome.model.User;
import java.util.Calendar;
public class ShowUserProfileFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {
    private ShowUserProfileViewModel showUserProfileViewModel;
    private FragmentShowProfileUserBinding binding;
    DatePickerDialog picker;
    String encodedImage;
    PickImage pickImage;
    String id="1";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        showUserProfileViewModel =
                new ViewModelProvider(this).get(ShowUserProfileViewModel.class);

        binding = FragmentShowProfileUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.dateUser.setOnClickListener(this);
        binding.dateUser.setOnFocusChangeListener(this);


      binding.imgUserProfile.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Toast.makeText(getActivity(), "jjjjjjjjjjjjj", Toast.LENGTH_SHORT).show();
              pickImage = new PickImage(getActivity(), binding.imgUserProfile);

              encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.imgUserProfile.getDrawable()).getBitmap());
              showUserProfileViewModel.updateImageUser(getActivity(),
                      id,encodedImage)
                      .observe(getViewLifecycleOwner(), new Observer<String>() {

                          @Override
                          public void onChanged(String s) {
                              Toast.makeText(getActivity(), encodedImage, Toast.LENGTH_SHORT).show();

                          }
                      });


          }
      });





        showUserProfileViewModel.getUserProfile(getActivity(), id).observe(getActivity(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                binding.GetFirstName.setText(user.getL_name());
                binding.GetLastName.setText(user.getF_name());
                binding.GetEmailUser.setText(user.getEmail());
                binding.GetDescriptionUser.setText(user.getDescription());
                binding.GetgenderUser.setText(user.getGender());
                binding.GetPhoneUser.setText(user.getPhone());
                binding.GetLocationUser.setText(user.getLocation());
                binding.GetdateUser.setText(user.getDate());

            }
        });


        binding.btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.imgUserProfile.getDrawable()).getBitmap());
                showUserProfileViewModel.updateUser(getActivity(),
                        id,
                        binding.FirstName.getEditText(),
                        binding.LastName.getEditText(),
                        binding.DescriptionUser.getEditText(),
                        binding.dateUser.getEditText(),
                        binding.LocationUser.getEditText())
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
                binding.dateUser.getEditText().setText(year + "-" + month + "-" + day);
            }
        }, year, month, day);
        picker.show();
    }
}