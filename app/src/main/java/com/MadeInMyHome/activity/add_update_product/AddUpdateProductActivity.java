package com.MadeInMyHome.activity.add_update_product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MadeInMyHome.databinding.ActivityAddUpdateProductBinding;

public class AddUpdateProductActivity extends AppCompatActivity {

    ActivityAddUpdateProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityAddUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}