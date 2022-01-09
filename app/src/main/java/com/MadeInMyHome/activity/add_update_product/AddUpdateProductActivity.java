package com.MadeInMyHome.activity.add_update_product;

import static com.MadeInMyHome.utilities.General.getSharedPreference;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.show_product.ProductViewModel;
import com.MadeInMyHome.activity.ui.MainActivity;
import com.MadeInMyHome.activity.ui.category_product.CategoryProductViewModel;
import com.MadeInMyHome.activity.user.UserProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.component.PickImage;
import com.MadeInMyHome.component.convertToString;
import com.MadeInMyHome.databinding.ActivityAddUpdateProductBinding;
import com.MadeInMyHome.model.Category;
import com.MadeInMyHome.model.Images;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddUpdateProductActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    ActivityAddUpdateProductBinding binding;
    CategoryProductViewModel categoryProductViewModel;
    AddUpdateProductViewModel addUpdateProductViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;
    ProductViewModel productViewModel;

    ArrayList<String> categoryName = new ArrayList<>();
    ArrayList<Category> categoryArrayList;
    ArrayList<ImageView> imageViewArrayList = new ArrayList<>();
    ArrayList<Images> updateImagesViewArrayList = new ArrayList<>();

    DatePickerDialog picker;
    Bitmap drawable, main;
    String name, price, size, unit, description, discount, discountDate, category, encodedImage;
    String encodedImage1, encodedImage2, encodedImage3, token;
    String id_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categoryProductViewModel = ViewModelProviders.of(this).get(CategoryProductViewModel.class);
        addUpdateProductViewModel = ViewModelProviders.of(this).get(AddUpdateProductViewModel.class);
        showUserProfileViewModel = ViewModelProviders.of(this).get(ShowUserProfileViewModel.class);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        drawable = ((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap();
        token = getSharedPreference(this, "token");

        binding.discountDate.setOnClickListener(this);
        binding.discountDate.setOnFocusChangeListener(this);

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

                        encodedImage1 = !(drawable == ((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap()) ? new convertToString().convertToString(((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap()) : null;
                        encodedImage2 = !(drawable == ((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap()) ? new convertToString().convertToString(((BitmapDrawable) binding.multiImage1.getDrawable()).getBitmap()) : null;
                        encodedImage3 = !(drawable == ((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap()) ? new convertToString().convertToString(((BitmapDrawable) binding.multiImage2.getDrawable()).getBitmap()) : null;

                        showUserProfileViewModel.getUserProfile(AddUpdateProductActivity.this, token)
                                .observe(AddUpdateProductActivity.this, new Observer<User>() {
                                    @Override
                                    public void onChanged(User user) {
                                        addUpdateProductViewModel
                                                .addProduct(AddUpdateProductActivity.this, name,
                                                        description.equals("") ? null : description, encodedImage, price, size, unit,
                                                        discount.equals("") ? null : discount, discountDate.equals("") ? null : discountDate,
                                                        new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
                                                        getCategoryId(category), user.getId(),
                                                        encodedImage1, encodedImage2, encodedImage3, binding.add)
                                                .observe(AddUpdateProductActivity.this, new Observer<String>() {
                                                    @Override
                                                    public void onChanged(String s) {
                                                        Intent i = new Intent(AddUpdateProductActivity.this, MainActivity.class);
                                                        startActivity(i);
                                                    }
                                                });
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
                    updateImagesViewArrayList = images;

                    if (images.size() > 0)
                        new GlideImage(AddUpdateProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(0).getImage(), binding.multiImage);
                    if (images.size() > 1)
                        new GlideImage(AddUpdateProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(1).getImage(), binding.multiImage1);
                    if (images.size() > 2)
                        new GlideImage(AddUpdateProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(2).getImage(), binding.multiImage2);
                }
            });

            binding.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.update.setClickable(false);
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
                                    encodedImage = new convertToString().convertToString(r.getBitmap());
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

    public void setMultiImage(View view,ArrayList<Images> imageViewArrayList) {
        Toast.makeText(this, "" + (((BitmapDrawable) ((ImageView) view).getDrawable()).getBitmap() == drawable), Toast.LENGTH_SHORT).show();
        if (drawable == ((BitmapDrawable) binding.multiImage.getDrawable()).getBitmap()) {
            PickImageDialog.build(new PickSetup())
                    .setOnPickResult(new IPickResult() {
                        @Override
                        public void onPickResult(PickResult r) {
                            //TODO: do what you have to...
                            if (imageViewArrayList != null) {
                                addImage(binding.multiImage, id_product, r);
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
                                addImage(binding.multiImage1, id_product, r);
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
                                addImage(binding.multiImage2, id_product, r);
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
                                    updateImage(view, updateImagesViewArrayList.get(0).getId(), r);
                                } else if (view.getId() == R.id.multiImage1) {
                                    updateImage(view, updateImagesViewArrayList.get(1).getId(), r);
                                } else {
                                    updateImage(view, updateImagesViewArrayList.get(2).getId(), r);
                                }
                            } else {
                                ((ImageView) view).setImageBitmap(r.getBitmap());
                            }
                        }
                    }).show(this);
        }
    }

    public void updateImage(View view, String id_product_image, PickResult r) {
        encodedImage = new convertToString().convertToString(r.getBitmap());
        addUpdateProductViewModel.updateProductMultiImage(AddUpdateProductActivity.this, id_product_image, encodedImage)
                .observe(AddUpdateProductActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        ((ImageView) view).setImageBitmap(r.getBitmap());
                    }
                });
    }
    public void addImage(View view, String id_product, PickResult r) {
        encodedImage = new convertToString().convertToString(r.getBitmap());
        addUpdateProductViewModel.addMultiImage(AddUpdateProductActivity.this, id_product, encodedImage)
                .observe(AddUpdateProductActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        ((ImageView) view).setImageBitmap(r.getBitmap());
                        Images i=new Images();
                        i.setId(s);
                        updateImagesViewArrayList.add(i);
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

    @Override
    public void onClick(View v) {
        setDate();
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        if (b)
            setDate();
    }

    public void setDate() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                binding.discountDate.getEditText().setText(year + "-" + month + 1 + "-" + day);
            }
        }, year, month, day);
        picker.show();
    }
}