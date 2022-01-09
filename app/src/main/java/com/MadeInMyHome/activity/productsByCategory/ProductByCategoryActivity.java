package com.MadeInMyHome.activity.productsByCategory;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.MadeInMyHome.activity.ui.courses.CoursesViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterCourse;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.ActivityProductsBycategoryBinding;
import com.MadeInMyHome.model.Course;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;


public class ProductByCategoryActivity extends AppCompatActivity {

    RecycleAdapterProduct recycleAdapterProduct;
    RecycleAdapterCourse recycleAdapterCourse;
    ProductByCategoryViewModel productByCategoryViewModel;
    CoursesViewModel coursesViewModel;
    private ActivityProductsBycategoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductsBycategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productByCategoryViewModel = ViewModelProviders.of(this).get(ProductByCategoryViewModel.class);
        coursesViewModel = ViewModelProviders.of(this).get(CoursesViewModel.class);

        binding.recycleProductByCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        if (getIntent().getExtras().getString("category").equals("product")) {
            productByCategoryViewModel.getDataProductByCategory(this, getIntent().getExtras().getString("category_id"))
                    .observe(this, new Observer<ArrayList<Product>>() {
                        @Override
                        public void onChanged(ArrayList<Product> products) {
                            recycleAdapterProduct = new RecycleAdapterProduct(ProductByCategoryActivity.this, products, "product");
                            binding.recycleProductByCategory.setAdapter(recycleAdapterProduct);
                        }
                    });
        }else {
            coursesViewModel.getCourses(this,getIntent().getExtras().getString("category_id"),"0")
                    .observe(this, new Observer<ArrayList<Course>>() {
                        @Override
                        public void onChanged(ArrayList<Course> courses) {
                            recycleAdapterCourse = new RecycleAdapterCourse(ProductByCategoryActivity.this, courses, "course");
                            binding.recycleProductByCategory.setAdapter(recycleAdapterCourse);
                        }
                    });
        }
    }
}