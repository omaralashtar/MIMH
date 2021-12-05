package com.MadeInMyHome.Response;

import com.MadeInMyHome.model.Product;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ProductArrayListResponse {

    @SerializedName("result")
    private ArrayList<Product> arrayList;

    public ArrayList<Product> getArrayList() {
        return arrayList;
    }
}
