package com.MadeInMyHome.activity.add_update_product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.productsByCategory.ProductByCategoryViewModel;
import com.MadeInMyHome.activity.ui.category_product.CategoryProductViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterImages;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.databinding.ActivityAddUpdateProductBinding;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.model.Images;

import java.util.ArrayList;

public class AddUpdateProductActivity extends AppCompatActivity {

    ActivityAddUpdateProductBinding binding;
    CategoryProductViewModel categoryProductViewModel;
    AddUpdateProductViewModel addUpdateProductViewModel;

    RecycleAdapterImages recycleAdapterImages;

    ArrayList<String> categoriesArrayList;
    ArrayList<Bitmap> bitmapArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityAddUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categoryProductViewModel = ViewModelProviders.of(this).get(CategoryProductViewModel.class);
        addUpdateProductViewModel = ViewModelProviders.of(this).get(AddUpdateProductViewModel.class);

        binding.multiImageRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

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
        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PickImage(AddUpdateProductActivity.this, binding.image);
            }
        });
        binding.multiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PickImage(AddUpdateProductActivity.this, binding.multiImage);
                bitmapArrayList.add(((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap());
                recycleAdapterImages=new RecycleAdapterImages(AddUpdateProductActivity.this,bitmapArrayList);
                binding.multiImageRecyclerView.setAdapter(recycleAdapterImages);

                binding.multiImageRecyclerView.setVisibility(View.VISIBLE);
                binding.multiImage.setVisibility(View.GONE);
            }
        });

    }
}