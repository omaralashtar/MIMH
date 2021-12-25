package com.MadeInMyHome.activity.show_product;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.MadeInMyHome.R;
import com.MadeInMyHome.databinding.ActivityProductBinding;

public class ProductActivity extends AppCompatActivity {

    ActivityProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}