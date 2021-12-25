package com.MadeInMyHome.activity.productsByCategory;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.R;
import com.MadeInMyHome.adapter.RecycleAdapterProduct;
import com.MadeInMyHome.databinding.ActivityLoginSignUpBinding;
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
        binding = ActivityProductsBycategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


binding.recycleProductByCategory
        .setLayoutManager(new
                LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        getDataSomMeal();









    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public void getDataSomMeal()
    {

        int intentData=getIntent().getExtras().getInt("category");
        productByCategoryViewModel= ViewModelProviders.of(this).get(ProductByCategoryViewModel.class);
        productByCategoryViewModel.getDataProductByCategory(this, intentData).observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                recycleAdapterProduct =new RecycleAdapterProduct(ProductByCategoryActivity.this,products);
                binding.recycleProductByCategory.setAdapter(recycleAdapterProduct);



            }
        });


    }


}