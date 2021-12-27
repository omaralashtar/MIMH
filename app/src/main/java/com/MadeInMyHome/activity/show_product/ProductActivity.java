package com.MadeInMyHome.activity.show_product;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityProductBinding;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.utilities.constants;


public class ProductActivity extends AppCompatActivity {

    ProductViewModel productViewModel;
    ActivityProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.getProduct(this, getIntent().getExtras().getString("id")).observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                binding.name.setText(product.getName());
                binding.price.setText(String.valueOf(product.getPrice()));
                binding.size.setText(String.valueOf(product.getSize()));
                binding.unit.setText(product.getUnit());
                binding.category.setText(product.getCategory());
                binding.description.setText(product.getDescription());
                new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + product.getImage(), binding.image);

            }
        });

        /*productViewModel.getProductMultiImage(this,getIntent().getExtras().getString("id")).observe(this, new Observer<ArrayList<Images>>() {
            @Override
            public void onChanged(ArrayList<Images> images) {

            }
        });*/
    }
}