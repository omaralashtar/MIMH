package com.MadeInMyHome.activity.show_product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.show_course.CourseActivity;
import com.MadeInMyHome.activity.show_course.CourseViewModel;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityProductBinding;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.model.Images;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    ProductViewModel productViewModel;
    ActivityProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.getProduct(this,getIntent().getExtras().getString("id")).observe(this, new Observer<Product>() {
                    @Override
                    public void onChanged(Product product) {
                        binding.name.setText(product.getName());
                        binding.price.setText(String.valueOf(product.getPrice()));
                        binding.size.setText(String.valueOf(product.getSize()));
                        binding.unit.setText(product.getUnit());
                        binding.category.setText(product.getCategory());
                        binding.description.setText(product.getDescription());
                        new GlideImage(ProductActivity.this, constants.BASE_HOST +constants.IMAGE_PRODUCT+ product.getImage(), binding.image);

                    }
                });

        productViewModel.getProductMultiImage(this,getIntent().getExtras().getString("id")).observe(this, new Observer<ArrayList<Images>>() {
            @Override
            public void onChanged(ArrayList<Images> images) {

            }
        });
    }
}