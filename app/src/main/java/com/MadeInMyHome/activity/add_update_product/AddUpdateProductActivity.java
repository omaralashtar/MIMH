package com.MadeInMyHome.activity.add_update_product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.productsByCategory.ProductByCategoryViewModel;
import com.MadeInMyHome.activity.ui.category_product.CategoryProductViewModel;
import com.MadeInMyHome.databinding.ActivityAddUpdateProductBinding;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;

public class AddUpdateProductActivity extends AppCompatActivity {

    ActivityAddUpdateProductBinding binding;
    CategoryProductViewModel categoryProductViewModel;
    AddUpdateProductViewModel addUpdateProductViewModel;

    ArrayList<String> categoriesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityAddUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categoryProductViewModel = ViewModelProviders.of(this).get(CategoryProductViewModel.class);
        addUpdateProductViewModel = ViewModelProviders.of(this).get(AddUpdateProductViewModel.class);


        ArrayAdapter<String> unitAdapter =
                new ArrayAdapter<String>(this, R.layout.dropdown_menu_popup_item, getResources().getStringArray(R.array.unitList));

        binding.unitDropdown.setAdapter(unitAdapter);

        categoryProductViewModel.showCategoryProduct(this).observe(this, new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {

                for (Category category:categories){
                    categoriesArrayList.add(category.getName());
                }
                ArrayAdapter<String> unitAdapter =
                        new ArrayAdapter<String>(AddUpdateProductActivity.this, R.layout.dropdown_menu_popup_item,categoriesArrayList);

                binding.unitDropdown.setAdapter(unitAdapter);
            }
        });

    }
}