package com.MadeInMyHome.activity.search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.MadeInMyHome.adapter.RecycleAdapterUser;
import com.MadeInMyHome.databinding.FragmentSearchUserBinding;
import com.MadeInMyHome.model.User;

import java.util.ArrayList;


public class FragmentSearchUser extends Fragment {

    private FragmentSearchUserBinding binding;
    RecycleAdapterUser recycleAdapterUser;
    SearchViewModel searchViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding = FragmentSearchUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.userRecycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        getDataUser();


        binding.searchFilter.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                getDataUser();


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        binding.searchFilter.getEditText().setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {


                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    getDataUser();
                    return true;
                }
                return false;


            }
        });


        //button search

        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataUser();
            }
        });


        return root;
    }

    public void getDataUser() {

        searchViewModel.getAllUser(getActivity(), binding.searchFilter.getEditText().getText().toString()).observe(getActivity(), new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> user) {
                recycleAdapterUser = new RecycleAdapterUser(getActivity(), user);
                binding.userRecycle.setAdapter(recycleAdapterUser);

            }
        });


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}