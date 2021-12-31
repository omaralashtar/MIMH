package com.MadeInMyHome.activity.productsByCategory;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.R;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.ActivityCourseBinding;
import com.MadeInMyHome.databinding.ActivityProductsBycategoryBinding;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;


public class ProductByCategoryActivity extends AppCompatActivity {

    RecycleAdapterProduct recycleAdapterProduct;
    ProductByCategoryViewModel productByCategoryViewModel;
    private ActivityProductsBycategoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityProductsBycategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        productByCategoryViewModel = ViewModelProviders.of(this).get(ProductByCategoryViewModel.class);
        binding.recycleProductByCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        productByCategoryViewModel.getDataProductByCategory(this, getIntent().getExtras().getString("cat_id"))
                .observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                recycleAdapterProduct = new RecycleAdapterProduct(ProductByCategoryActivity.this, products);
                binding.recycleProductByCategory.setAdapter(recycleAdapterProduct);
            }
        });
    }

}