package com.MadeInMyHome.activity.productsByCategory;


import android.content.Context;
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
import com.MadeInMyHome.databinding.ActivityProductsBycategoryBinding;
import com.MadeInMyHome.model.Product;

import java.util.ArrayList;


public class productsBycategory extends AppCompatActivity {
    RecycleAdapterProduct recycleAdapterProduct;
    ProductByCategoryViewModel productByCategoryViewModel;
   private ActivityProductsBycategoryBinding binding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_bycategory);

binding.recycleProductByCategory
        .setLayoutManager(new
                LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        getDataSomMeal();

        binding.imgBtnRPWC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataSomMeal();

            }
        });

        binding.searchFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {

                getDataSomMeal();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });


        binding.searchFilter.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {


                    getDataSomMeal();


                    return true;
                }



                return false;


            }
        });




    }


    public void getDataSomMeal()
    {

        String intentData=String.valueOf(getIntent().getExtras().getInt("idCategory"));
        productByCategoryViewModel= ViewModelProviders.of(this).get(ProductByCategoryViewModel.class);
        productByCategoryViewModel.getDataProductByCategory(this,binding.searchFilter.getText().toString(), intentData).observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                recycleAdapterProduct =new RecycleAdapterProduct(productsBycategory.this,products);
                binding.recycleProductByCategory.setAdapter(recycleAdapterProduct);



            }
        });


    }


}