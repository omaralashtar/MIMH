package com.MadeInMyHome.activity.add_update_product;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.show_product.ProductViewModel;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.ui.category_product.CategoryProductViewModel;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.ActivityAddUpdateProductBinding;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.model.Images;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.utilities.constants;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddUpdateProductActivity extends AppCompatActivity {

    ActivityAddUpdateProductBinding binding;
    CategoryProductViewModel categoryProductViewModel;
    AddUpdateProductViewModel addUpdateProductViewModel;
    ProductViewModel productViewModel;

    ArrayList<String> categoryName = new ArrayList<>();
    ArrayList<Category> categoryArrayList;
    ArrayList<String> addMultiImagesArrayList = new ArrayList<>();
    ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    ArrayList<Images> updateImagesViewArrayList = null;

    Bitmap drawable, main;
    String name, price, size, unit, description, discount, discountDate, category, encodedImage;
    String id_user = "1";
    String id_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categoryProductViewModel = ViewModelProviders.of(this).get(CategoryProductViewModel.class);
        addUpdateProductViewModel = ViewModelProviders.of(this).get(AddUpdateProductViewModel.class);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        drawable = ((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap();

        ArrayAdapter<String> unitAdapter =
                new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, getResources().getStringArray(R.array.unitList));

        binding.unitDropdown.setAdapter(unitAdapter);

        categoryProductViewModel.showCategoryProduct(this).observe(this, new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(ArrayList<Category> categories) {
                categoryArrayList = categories;
                for (Category category : categories) {
                    categoryName.add(category.getName());
                }
                ArrayAdapter<String> categoryAdapter =
                        new ArrayAdapter<>(AddUpdateProductActivity.this, R.layout.dropdown_menu_popup_item, categoryName);

                binding.categoryDropdown.setAdapter(categoryAdapter);
            }
        });

        if (getIntent().getExtras().getString("name").equals("add")) {

            main = ((BitmapDrawable) binding.image.getDrawable()).getBitmap();

            binding.multiImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMultiImage(binding.multiImage);
                }
            });
            binding.multiImage1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMultiImage(binding.multiImage1);
                }
            });
            binding.multiImage2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMultiImage(binding.multiImage2);
                }
            });

            binding.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PickImage(AddUpdateProductActivity.this, binding.image);
                }
            });

            binding.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    binding.add.setClickable(false);
                    imageViewArrayList.add(binding.multiImage);
                    imageViewArrayList.add(binding.multiImage1);
                    imageViewArrayList.add(binding.multiImage2);

                    if (validate() && !(main == ((BitmapDrawable) binding.image.getDrawable()).getBitmap())) {
                        encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.image.getDrawable()).getBitmap());

                        for (int i = 0; i < 3; i++) {
                            if (!(drawable == ((BitmapDrawable) imageViewArrayList.get(i).getDrawable()).getBitmap())) {
                                addMultiImagesArrayList.add(new convertToString().convertToString(((BitmapDrawable) imageViewArrayList.get(i).getDrawable()).getBitmap()));
                            }
                        }

                        addUpdateProductViewModel
                                .addProduct(AddUpdateProductActivity.this, name,
                                        description.equals("") ? null : description, encodedImage, price, size, unit,
                                        discount.equals("") ? null : discount, discountDate.equals("") ? null : discountDate,
                                        new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                                        getCategoryId(category), id_user, addMultiImagesArrayList, binding.add).observe(AddUpdateProductActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Intent i = new Intent(AddUpdateProductActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        });
                    } else {
                        if (main == ((BitmapDrawable) binding.image.getDrawable()).getBitmap())
                            binding.image.setAnimation(AnimationUtils.loadAnimation(AddUpdateProductActivity.this, R.anim.shake));
                        binding.add.setClickable(true);
                    }
                }
            });
        } else {
            id_product = getIntent().getExtras().getString("id_product");

            binding.add.setVisibility(View.GONE);
            binding.update.setVisibility(View.VISIBLE);
            binding.photoDesc.setVisibility(View.VISIBLE);

            binding.multiImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMultiImage(binding.multiImage, updateImagesViewArrayList);
                }
            });
            binding.multiImage1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMultiImage(binding.multiImage1, updateImagesViewArrayList);
                }
            });
            binding.multiImage2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMultiImage(binding.multiImage2, updateImagesViewArrayList);
                }
            });


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
                    new GlideImage(AddUpdateProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + product.getImage(), binding.image);
                    main = ((BitmapDrawable) binding.image.getDrawable()).getBitmap();
                }
            });
            productViewModel.getProductMultiImage(this, id_product).observe(this, new Observer<ArrayList<Images>>() {
                @Override
                public void onChanged(ArrayList<Images> images) {
                    updateImagesViewArrayList = new ArrayList<>();
                    updateImagesViewArrayList = images;

                    if (images.size() == 1)
                        new GlideImage(AddUpdateProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(0).getImage(), binding.multiImage);
                    if (images.size() == 2)
                        new GlideImage(AddUpdateProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(1).getImage(), binding.multiImage1);
                    if (images.size() == 3)
                        new GlideImage(AddUpdateProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(2).getImage(), binding.multiImage2);
                }
            });

            binding.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (validate()) {
                        addUpdateProductViewModel.updateProduct(AddUpdateProductActivity.this, id_product, name,
                                description.equals("") ? null : description, price, size, unit,
                                discount.equals("") ? null : discount, discountDate.equals("") ? null : discountDate,
                                getCategoryId(category), binding.update).observe(AddUpdateProductActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                Intent i = new Intent(AddUpdateProductActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        });
                    } else {
                        binding.update.setClickable(true);
                    }
                }
            });
            binding.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PickImageDialog.build(new PickSetup())
                            .setOnPickResult(new IPickResult() {
                                @Override
                                public void onPickResult(PickResult r) {
                                    //TODO: do what you have to...
                                    encodedImage = new convertToString().convertToString(((BitmapDrawable) binding.image.getDrawable()).getBitmap());
                                    addUpdateProductViewModel.updateProductImage(AddUpdateProductActivity.this, id_product, encodedImage)
                                            .observe(AddUpdateProductActivity.this, new Observer<String>() {
                                                @Override
                                                public void onChanged(String s) {
                                                    binding.image.setImageBitmap(r.getBitmap());
                                                }
                                            });
                                }
                            }).show(AddUpdateProductActivity.this);
                }
            });
        }
    }

    public Boolean validate() {
        name = binding.name.getEditText().getText().toString();
        price = binding.price.getEditText().getText().toString();
        size = binding.size.getEditText().getText().toString();
        unit = binding.unit.getEditText().getText().toString();
        category = binding.category.getEditText().getText().toString();
        description = binding.description.getEditText().getText().toString();
        discount = binding.discount.getEditText().getText().toString();
        discountDate = binding.discountDate.getEditText().getText().toString();

        if (!name.equals("") && !price.equals("") && !size.equals("") &&
                !unit.equals("") && !category.equals("")) {
            return true;
        } else {
            if (name.equals(""))
                binding.name.getEditText().setError(getResources().getText(R.string.error));
            if (price.equals(""))
                binding.price.getEditText().setError(getResources().getText(R.string.error));
            if (size.equals(""))
                binding.size.getEditText().setError(getResources().getText(R.string.error));
            if (unit.equals(""))
                binding.unitDropdown.setError(getResources().getText(R.string.error));
            if (category.equals(""))
                binding.categoryDropdown.setError(getResources().getText(R.string.error));
            if (main == ((BitmapDrawable) binding.image.getDrawable()).getBitmap())
                binding.image.setAnimation(AnimationUtils.loadAnimation(AddUpdateProductActivity.this, R.anim.shake));
            return false;
        }
    }

    public void setMultiImage(View view) {
        setMultiImage(view, null);
    }

    public void setMultiImage(View view, ArrayList<Images> imageViewArrayList) {
        Toast.makeText(this, "" + (((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap() == drawable), Toast.LENGTH_SHORT).show();
        if (drawable == ((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap()) {
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            if (imageViewArrayList != null) {
                                updateImage(view, imageViewArrayList.get(0).getId(), r);
                            } else {
                                binding.multiImage.setImageBitmap(r.getBitmap());
                            }
                        }
                    }).show(this);

        } else if (drawable == ((BitmapDrawable) binding.multiImage1.getDrawable()).getBitmap()) {
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            if (imageViewArrayList != null) {
                                updateImage(view, imageViewArrayList.get(1).getId(), r);
                            } else {
                                binding.multiImage1.setImageBitmap(r.getBitmap());
                            }
                        }
                    }).show(this);

        } else if (drawable == ((BitmapDrawable) binding.multiImage2.getDrawable()).getBitmap()) {
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            if (imageViewArrayList != null) {
                                updateImage(view, imageViewArrayList.get(2).getId(), r);
                            } else {
                                binding.multiImage2.setImageBitmap(r.getBitmap());
                            }
                        }
                    }).show(this);
        } else {
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            if (imageViewArrayList != null) {
                                if (view.getId() == R.id.multiImage) {
                                    updateImage(view, imageViewArrayList.get(0).getId(), r);
                                } else if (view.getId() == R.id.multiImage1) {
                                    updateImage(view, imageViewArrayList.get(1).getId(), r);
                                } else {
                                    updateImage(view, imageViewArrayList.get(2).getId(), r);
                                }
                            } else {
                                ((ImageView) view).setImageBitmap(r.getBitmap());
                            }
                        }
                    }).show(this);
        }
    }

    public void updateImage(View view, String id_product_image, PickResult r) {
        encodedImage = new convertToString().convertToString(((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap());
        addUpdateProductViewModel.updateProductMultiImage(AddUpdateProductActivity.this, id_product_image, encodedImage)
                .observe(AddUpdateProductActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        ((ImageView) view).setImageBitmap(r.getBitmap());
                    }
                });
    }

    public String getCategoryId(String name) {
        for (Category category : categoryArrayList) {
            if (category.getName().equals(name))
                return category.getId();
        }
        return "0";
    }
}