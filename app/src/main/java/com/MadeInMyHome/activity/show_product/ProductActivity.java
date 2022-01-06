package com.MadeInMyHome.activity.show_product;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityProductBinding;
import com.MadeInMyHome.model.Images;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;


public class ProductActivity extends AppCompatActivity {

    ProductViewModel productViewModel;
    ActivityProductBinding binding;

    String id_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        id_product = getIntent().getExtras().getString("id_product");

        productViewModel.getProduct(this, id_product).observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                binding.name.setText(product.getName());
                binding.price.setText(String.valueOf(product.getPrice()));
                binding.size.setText(String.valueOf(product.getSize()));
                binding.unit.setText(product.getUnit());
                binding.category.setText(product.getCategory());
                binding.description.setText(product.getDescription() == null ? "" : product.getDescription());
                new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + product.getImage(), binding.image);

            }
        });

        productViewModel.getProductMultiImage(this, id_product).observe(this, new Observer<ArrayList<Images>>() {
            @Override
            public void onChanged(ArrayList<Images> images) {
                if (images.size() == 1)
                    new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(0).getImage(), binding.multiImage);
                if (images.size() == 2)
                    new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(1).getImage(), binding.multiImage1);
                if (images.size() == 3)
                    new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(2).getImage(), binding.multiImage2);
            }
        });
    }
}