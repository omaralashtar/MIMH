package com.MadeInMyHome.activity.show_product;

import static com.MadeInMyHome.utilities.General.getToken;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.R;
import com.MadeInMyHome.activity.user.UserProfile.ShowUserProfileViewModel;
import com.MadeInMyHome.adapter.RecycleAdapterRate;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityProductBinding;
import com.MadeInMyHome.model.Images;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.Rate;
import com.MadeInMyHome.model.User;
import com.MadeInMyHome.utilities.constants;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    RecycleAdapterRate recycleAdapterRate,recycleAdapterMyRate;

    ProductViewModel productViewModel;
    ShowUserProfileViewModel showUserProfileViewModel;

    ActivityProductBinding binding;

    String id_product, rating_float;
    boolean isFav = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id_product = getIntent().getExtras().getString("id_product");

        binding.commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.mycommentsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        showUserProfileViewModel = new ViewModelProvider(this).get(ShowUserProfileViewModel.class);
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        //get product
        productViewModel.getProduct(this, id_product).observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {

                binding.name.setText(product.getName());
                binding.price.setText(String.valueOf(product.getPrice()) + "jd");
                binding.size.setText(String.valueOf(product.getSize()));
                binding.unit.setText(product.getUnit());
                binding.category.setText(product.getCategory());
                if (product.getDescription()!=null) {
                    binding.description.setText(product.getDescription());
                } else {
                    binding.desc.setVisibility(View.GONE);
                    binding.description.setVisibility(View.GONE);
                }
                new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + product.getImage(), binding.image);

            }
        });

        productViewModel.getProductMultiImage(this, id_product).observe(this, new Observer<ArrayList<Images>>() {
            @Override
            public void onChanged(ArrayList<Images> images) {

                if (images.size() > 0)
                    new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(0).getImage(), binding.multiImage);
                if (images.size() > 1)
                    new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(1).getImage(), binding.multiImage1);
                if (images.size() > 2)
                    new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + images.get(2).getImage(), binding.multiImage2);
            }
        });

        showUserProfileViewModel.getUserProfile(this, getToken(this)).observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                productViewModel.getFavorite(ProductActivity.this, user.getId(), id_product)
                        .observe(ProductActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                isFav = true;
                                binding.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                            }
                        });
                binding.favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isFav) {
                            productViewModel.deleteFavorite(ProductActivity.this, user.getId(), id_product)
                                    .observe(ProductActivity.this, new Observer<String>() {
                                        @Override
                                        public void onChanged(String s) {
                                            isFav = false;
                                            binding.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                                        }
                                    });
                        } else {
                            productViewModel.addFavorite(ProductActivity.this, user.getId(), id_product)
                                    .observe(ProductActivity.this, new Observer<String>() {
                                        @Override
                                        public void onChanged(String s) {
                                            isFav = true;
                                            binding.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                                        }
                                    });
                        }
                    }
                });

                //Get MyRate
                productViewModel.getMyRate(ProductActivity.this,user.getId(), id_product)
                        .observe(ProductActivity.this, new Observer<ArrayList<Rate>>() {
                            @Override
                            public void onChanged(ArrayList<Rate> rate) {
                                recycleAdapterMyRate = new RecycleAdapterRate(ProductActivity.this, rate,productViewModel,id_product);
                                binding.mycommentsRecyclerView.setAdapter(recycleAdapterMyRate);
                                binding.add.setVisibility(View.GONE);
                            }
                        });

                //Get Rate
                productViewModel.getRate(ProductActivity.this, id_product)
                        .observe(ProductActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                binding.productRate.setRating(s!=null?Float.parseFloat(s):5);
                            }
                        });

                productViewModel.getAllRate(ProductActivity.this, id_product, "0")
                        .observe(ProductActivity.this, new Observer<ArrayList<Rate>>() {
                            @Override
                            public void onChanged(ArrayList<Rate> rate) {
                                recycleAdapterRate = new RecycleAdapterRate(ProductActivity.this, rate,null,null);
                                binding.commentsRecyclerView.setAdapter(recycleAdapterRate);
                            }
                        });

                //Add product
                binding.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialogAddComment(user.getId());
                    }
                });
            }
        });
    }


    void showDialogAddComment(String id_user) {

        final BottomSheetDialog bt = new BottomSheetDialog(ProductActivity.this, R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(ProductActivity.this).inflate(R.layout.bottom_sheet_add_comment, null);
        TextInputLayout commentText = view.findViewById(R.id.comment);
        RatingBar ratingBar = view.findViewById(R.id.ratingBar);
        rating_float = String.valueOf(ratingBar.getRating());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rating_float = String.valueOf(v);
            }
        });

        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productViewModel.addComment(ProductActivity.this, id_user, id_product, rating_float, commentText.getEditText().getText().toString())
                        .observe(ProductActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                rate(id_user,id_product);
                            }
                        });
                bt.dismiss();
            }
        });
        bt.setContentView(view);
        bt.show();
    }

    public void rate(String id_user,String id_product){

        //Get MyRate
        productViewModel.getMyRate(ProductActivity.this,id_user, id_product)
                .observe(ProductActivity.this, new Observer<ArrayList<Rate>>() {
                    @Override
                    public void onChanged(ArrayList<Rate> rate) {
                        recycleAdapterMyRate = new RecycleAdapterRate(ProductActivity.this, rate,productViewModel,id_product);
                        binding.mycommentsRecyclerView.setAdapter(recycleAdapterMyRate);
                        binding.add.setVisibility(View.GONE);
                    }
                });

        //Get Rate
        productViewModel.getRate(ProductActivity.this, id_product)
                .observe(ProductActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        binding.productRate.setRating(s!=null?Float.parseFloat(s):5);
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}


