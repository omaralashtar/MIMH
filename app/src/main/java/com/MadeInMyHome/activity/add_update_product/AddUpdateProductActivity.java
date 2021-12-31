package com.MadeInMyHome.activity.add_update_product;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.ui.category_product.CategoryProductViewModel;
import com.MadeInMyHome.adapter.ArrayAdapterCategory;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.databinding.ActivityAddUpdateProductBinding;
import com.MadeInMyHome.model.Category;

import java.util.ArrayList;

public class AddUpdateProductActivity extends AppCompatActivity {

    ActivityAddUpdateProductBinding binding;
    CategoryProductViewModel categoryProductViewModel;
    AddUpdateProductViewModel addUpdateProductViewModel;

    ArrayAdapterCategory categoryArrayAdapter;
    ArrayList<Bitmap> bitmapArrayList=new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
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

                categoryArrayAdapter =
                        new ArrayAdapterCategory(AddUpdateProductActivity.this, categories);

                binding.categoryDropdown.setAdapter(categoryArrayAdapter);
            }
        });

        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PickImage(AddUpdateProductActivity.this, binding.image);
            }
        });

        binding.multiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage pickImage=new PickImage(AddUpdateProductActivity.this, binding.multiImage);
                if(pickImage.end) {
                    bitmapArrayList.add(((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap());
                    Toast.makeText(AddUpdateProductActivity.this, bitmapArrayList.get(0)+"", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}