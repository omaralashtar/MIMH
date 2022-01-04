package com.MadeInMyHome.activity.add_update_product;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.show_product.ProductViewModel;
import com.MadeInMyHome.activity.ui.category_product.CategoryProductViewModel;
import com.MadeInMyHome.adapter.ArrayAdapterCategory;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.ActivityAddUpdateProductBinding;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.model.Product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddUpdateProductActivity extends AppCompatActivity {

    ActivityAddUpdateProductBinding binding;
    CategoryProductViewModel categoryProductViewModel;
    AddUpdateProductViewModel addUpdateProductViewModel;
    ProductViewModel productViewModel;

    ArrayAdapterCategory categoryArrayAdapter;
    ArrayList<String> multiImagesArrayList = new ArrayList<>();
    ArrayList<ImageView> imageViewArrayList = new ArrayList<>();

    Drawable drawable, main;
    String name, price, size, unit, description, discount, discountDate, category, encodedImage;
    String id_user = "1";
    String id_product = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categoryProductViewModel = ViewModelProviders.of(this).get(CategoryProductViewModel.class);
        addUpdateProductViewModel = ViewModelProviders.of(this).get(AddUpdateProductViewModel.class);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);


        main = binding.image.getDrawable();
        drawable = binding.multiImage.getDrawable();

        ArrayAdapter<String> unitAdapter =
                new ArrayAdapter<String>(this, R.layout.dropdown_menu_popup_item, getResources().getStringArray(R.array.unitList));

        binding.unitDropdown.setAdapter(unitAdapter);

        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PickImage(AddUpdateProductActivity.this, binding.image);
            }
        });

        categoryProductViewModel.showCategoryProduct(this).observe(this, new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {

                categoryArrayAdapter =
                        new ArrayAdapterCategory(AddUpdateProductActivity.this, categories);

                binding.categoryDropdown.setAdapter(categoryArrayAdapter);
            }
        });

        if (getIntent().getExtras().getString("name").equals("add")) {
            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = binding.name.getEditText().getText().toString();
                    price = binding.price.getEditText().getText().toString();
                    size = binding.size.getEditText().getText().toString();
                    unit = binding.unit.getEditText().getText().toString();
                    category = binding.category.getEditText().getText().toString();
                    description = binding.description.getEditText().getText().toString();
                    discount = binding.discount.getEditText().getText().toString();
                    discountDate = binding.discountDate.getEditText().getText().toString();

                    imageViewArrayList.add(binding.multiImage);
                    imageViewArrayList.add(binding.multiImage1);
                    imageViewArrayList.add(binding.multiImage2);

                    if (!name.equals("") && !price.equals("") && !size.equals("") &&
                            !unit.equals("") && !category.equals("") &&
                            !(main == binding.image.getDrawable())) {
                        encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.image.getDrawable()).getBitmap());

                        for (int i = 0; i < 3; i++) {
                            if (drawable == imageViewArrayList.get(i).getDrawable()) {
                                multiImagesArrayList.add(new convertToString().convertToString(((BitmapDrawable) imageViewArrayList.get(i).getDrawable()).getBitmap()));
                            }
                        }

                        addUpdateProductViewModel
                                .addProduct(AddUpdateProductActivity.this, name,
                                        description.equals("") ? null : description, encodedImage, price, size, unit,
                                        discount.equals("") ? null : discount, discountDate.equals("") ? null : discountDate,
                                        new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                                        categoryArrayAdapter.getIdView(category), id_user, multiImagesArrayList);
                    } else {
                        if (name.equals(""))
                            binding.name.setError(getResources().getText(R.string.error));
                        if (price.equals(""))
                            binding.price.setError(getResources().getText(R.string.error));
                        if (size.equals(""))
                            binding.size.setError(getResources().getText(R.string.error));
                        if (unit.equals(""))
                            binding.unit.setError(getResources().getText(R.string.error));
                        if (category.equals(""))
                            binding.category.setError(getResources().getText(R.string.error));
                        if (main == binding.image.getDrawable())
                            binding.image.startAnimation(AnimationUtils.loadAnimation(AddUpdateProductActivity.this, R.anim.shake));
                    }
                }
            });
        } else {
            binding.add.setVisibility(View.GONE);
            binding.update.setVisibility(View.VISIBLE);
            binding.photoDesc.setVisibility(View.VISIBLE);

            productViewModel.getProduct(this, id_product).observe(this, new Observer<Product>() {
                @Override
                public void onChanged(Product product) {
                    binding.name.getEditText().setText(product.getName());
                    binding.price.getEditText().setText(product.getPrice());
                    binding.size.getEditText().setText(product.getSize());
                    binding.unit.getEditText().setText(product.getUnit());
                    binding.description.getEditText().setText(product.getDescription() == null ? "" : product.getDescription());
                    binding.discount.getEditText().setText(product.getDiscount() == null ? "" : product.getDiscount());
                    binding.discountDate.getEditText().setText(product.getDiscount_date() == null ? "" : product.getDiscount_date());
                    binding.category.getEditText().setText(product.getCategory());
                }
            });

        }
    }

    public void setMultiImage(View view) {
        setMultiImage(view, null);
        
    }

    public void setMultiImage(View view, ArrayList<ImageView> imageViewArrayList) {
        if (view.getId() == R.id.multiImage) {
            setImage(binding.multiImage);
        } else if (view.getId() == R.id.multiImage1) {
            setImage(binding.multiImage1);
        } else {
            setImage(binding.multiImage2);
        }
    }

    public void setImage(ImageView image) {
        if (drawable == binding.multiImage.getDrawable()) {
            new PickImage(AddUpdateProductActivity.this, binding.multiImage);
        } else if (drawable == binding.multiImage1.getDrawable()) {
            new PickImage(AddUpdateProductActivity.this, binding.multiImage1);
        } else if (drawable == binding.multiImage2.getDrawable()) {
            new PickImage(AddUpdateProductActivity.this, binding.multiImage2);
        } else {
            new PickImage(AddUpdateProductActivity.this, image);
        }
    }
}