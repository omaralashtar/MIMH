package com.MadeInMyHome.activity.show_product;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.R;
import com.MadeInMyHome.adapter.RecycleAdapterRate;
import com.MadeInMyHome.component.GlideImage;
import com.MadeInMyHome.databinding.ActivityProductBinding;
import com.MadeInMyHome.model.Product;
import com.MadeInMyHome.model.Rate;
import com.MadeInMyHome.utilities.constants;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class ProductActivity extends AppCompatActivity {
    RecycleAdapterRate recycleAdapterRate;
    CommentViewModel commentViewModel;
    ProductViewModel productViewModel;
     ActivityProductBinding binding;
    String id_user = "1";
    String id_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        commentViewModel = ViewModelProviders.of(this).get(CommentViewModel.class);

        productViewModel.getProduct(this, getIntent().getExtras().getString("id")).observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {

                id_product = product.getId();
                binding.name.setText(product.getName());
                binding.price.setText(String.valueOf(product.getPrice()));
                binding.size.setText(String.valueOf(product.getSize()));
                binding.unit.setText(product.getUnit());
                binding.category.setText(product.getCategory());
                binding.description.setText(product.getDescription());
                new GlideImage(ProductActivity.this, constants.BASE_HOST + constants.IMAGE_PRODUCT + product.getImage(), binding.image);

            }
        });


        commentViewModel.getRateAll(this, getIntent().getExtras().getString("id"), "5").observe(this, new Observer<ArrayList<Rate>>() {
            @Override
            public void onChanged(ArrayList<Rate> rate) {
                recycleAdapterRate = new RecycleAdapterRate(ProductActivity.this, rate);
                binding.commentsRecyclerView.setAdapter(recycleAdapterRate);
            }
        });


        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddComment();
            }
        });
    }


    void showDialogAddComment() {

        final BottomSheetDialog bt = new BottomSheetDialog(ProductActivity.this, R.style.BottomSheetDialogTheme);
        View view = LayoutInflater.from(ProductActivity.this).inflate(R.layout.bottom_sheet_add_comment, null);
        TextInputLayout commentText = view.findViewById(R.id.comment);
        view.findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(ProductActivity.this, commentText.getEditText().getText().toString(), Toast.LENGTH_LONG).show();


                commentViewModel.addComment(ProductActivity.this, id_user, id_product, "2", commentText.getEditText().getText().toString()).observe(ProductActivity.this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {

                        Toast.makeText(ProductActivity.this, "add Comment", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(ProductActivity.this, ProductActivity.class);
                        startActivity(i);
                        finish();


                    }
                });


                bt.dismiss();
            }
        });
        bt.setContentView(view);
        bt.show();


    }


}