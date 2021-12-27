package com.MadeInMyHome.WebService;


import com.MadeInMyHome.Response.CategoryArrayListResponse;
import com.MadeInMyHome.Response.CourseArrayListResponse;
import com.MadeInMyHome.Response.ImageArrayListResponse;
import com.MadeInMyHome.Response.ProductArrayListResponse;
import com.MadeInMyHome.Response.RateArrayListResponse;
import com.MadeInMyHome.Response.ResultResponse;
import com.MadeInMyHome.Response.ResultUserResponse;
import com.MadeInMyHome.Response.UserArrayListResponse;
import com.MadeInMyHome.Response.VideoArrayListResponse;
import com.MadeInMyHome.utilities.constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AppApi {
    //USER

    @FormUrlEncoded
    @POST(constants.USER + constants.SING_UP)
    Call<ResultUserResponse> signUp(@Field("email") String email,
                                    @Field("password") String password,
                                    @Field("f_name") String f_name,
                                    @Field("l_name") String l_name,
                                    @Field("date") String date,
                                    @Field("gender") String gender,
                                    @Field("phone") String phone,
                                    @Field("image") String image);

    @FormUrlEncoded
    @POST(constants.USER + constants.LOGIN)
    Call<ResultUserResponse> login(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST(constants.USER + constants.GET)
    Call<UserArrayListResponse> getUser(@Field("id") String id);

    @FormUrlEncoded
    @POST(constants.USER + constants.UPDATE)
    Call<ResultResponse> updateUser(@Field("id") String id,
                                    @Field("f_name") String f_name,
                                    @Field("l_name") String l_name,
                                    @Field("description") String description,
                                    @Field("date") String date,
                                    @Field("gender") String gender,
                                    @Field("phone") String phone,
                                    @Field("location") String location);

    @FormUrlEncoded
    @POST(constants.USER + constants.UPDATE_IMAGE)
    Call<ResultResponse> updateUserImage(@Field("id") String id,
                                         @Field("image") String image);

    @FormUrlEncoded
    @POST(constants.USER + constants.UPDATE_PASSWORD)
    Call<ResultResponse> updateUserPassword(@Field("id") String id,
                                            @Field("password") String password,
                                            @Field("newpassword") String newpassword);

    //PRODUCT

    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.ADD)
    Call<ResultResponse> addProduct(@Field("name") String name,
                                    @Field("description") String description,
                                    @Field("image") String image,
                                    @Field("price") String price,
                                    @Field("size") String size,
                                    @Field("unit") String unit,
                                    @Field("discount") String discount,
                                    @Field("discount_date") String discount_date,
                                    @Field("status") String status,
                                    @Field("product_date") String product_date,
                                    @Field("Category") String Category,
                                    @Field("id_user") String id_user,
                                    @Field("images") ArrayList<String> images);

    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.GET_MY_PRODUCT)
    Call<ProductArrayListResponse> getMyProduct(@Field("id") String id);

    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.GET)
    Call<ProductArrayListResponse> getProduct(@Field("id") String id);

    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.GET_ALL)
    Call<ProductArrayListResponse> getAllProduct(@Field("next") String next);

    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.GET_CATEGORY)
    Call<CategoryArrayListResponse> getCategoryProduct(@Field("filter") String filter);


    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.GET_PRODUCT_BY_CATEGORY)
    Call<ProductArrayListResponse> getProductByCategory(@Field("id") String id);


    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.GET_MULTI_IMAGE)
    Call<ImageArrayListResponse> getMultiImagesProduct(@Field("id") String id);

    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.UPDATE)
    Call<ResultResponse> updateProduct(@Field("id") String id,
                                       @Field("name") String name,
                                       @Field("description") String description,
                                       @Field("price") String price,
                                       @Field("size") String size,
                                       @Field("unit") String unit,
                                       @Field("discount") String discount,
                                       @Field("discount_date") String discount_date,
                                       @Field("status") String status,
                                       @Field("Category") String Category);

    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.UPDATE_IMAGE)
    Call<ResultResponse> updateProductImage(@Field("id") String id,
                                            @Field("image") String image);

    @FormUrlEncoded
    @POST(constants.PRODUCT + constants.UPDATE_MULTI_IMAGE)
    Call<ResultResponse> updateProductMultiImages(@Field("id") String id,
                                                  @Field("image") String image);

    //COURSE

    @FormUrlEncoded
    @POST(constants.COURSE + constants.GET)
    Call<CourseArrayListResponse> getCourse(@Field("id") String id);

    @FormUrlEncoded
    @POST(constants.COURSE + constants.GET_ALL)
    Call<CourseArrayListResponse> getAllCourse(@Field("next") String next);

    @FormUrlEncoded
    @POST(constants.COURSE + constants.GET_CATEGORY)
    Call<CategoryArrayListResponse> getCategoryCourse(@Field("filter") String filter);

    //ENROLL

    @FormUrlEncoded
    @POST(constants.ENROLL + constants.ADD)
    Call<ResultResponse> addEnroll(@Field("id_user") String id_user,
                                   @Field("id_product") String id_product,
                                   @Field("enroll_date") String enroll_date);

    @FormUrlEncoded
    @POST(constants.ENROLL + constants.GET_ALL)
    Call<CourseArrayListResponse> getAllEnroll(@Field("id_user") String id_user,
                                               @Field("next") String next);

    //RATE

    @FormUrlEncoded
    @POST(constants.RATE + constants.ADD)
    Call<ResultResponse> addRate(@Field("id_user") String id_user,
                                 @Field("id_product") String id_product,
                                 @Field("rating") String rating,
                                 @Field("comment") String comment);

    @FormUrlEncoded
    @POST(constants.RATE + constants.DELETE)
    Call<ResultResponse> deleteRate(@Field("id_user") String id_user,
                                    @Field("id_product") String id_product);

    @FormUrlEncoded
    @POST(constants.RATE + constants.GET_ALL)
    Call<RateArrayListResponse> getAllRate(@Field("id") String id,
                                           @Field("next") String next);

    @FormUrlEncoded
    @POST(constants.RATE + constants.UPDATE)
    Call<ResultResponse> updateRate(@Field("id_user") String id_user,
                                    @Field("id_product") String id_product,
                                    @Field("rating") String rating,
                                    @Field("comment") String comment);

    //VIDEO

    @FormUrlEncoded
    @POST(constants.VIDEO + constants.GET)
    Call<VideoArrayListResponse> getVideo(@Field("id") String id);

    @FormUrlEncoded
    @POST(constants.VIDEO + constants.REPORT)
    Call<ResultResponse> addReport(@Field("id_user") String id_user,
                                   @Field("id_video") String id_video,
                                   @Field("message") String message);

    @FormUrlEncoded
    @POST(constants.VIDEO + constants.WATCH)
    Call<ResultResponse> addWatch(@Field("id_user") String id_user,
                                  @Field("id_video") String id_video);

    //FAVORITE

    @FormUrlEncoded
    @POST(constants.FAVORITE + constants.ADD)
    Call<ResultResponse> addFavorite(@Field("id_user") String id_user,
                                     @Field("id_product") String id_product);

    @FormUrlEncoded
    @POST(constants.FAVORITE + constants.DELETE)
    Call<ResultResponse> deleteFavorite(@Field("id_user") String id_user,
                                        @Field("id_product") String id_product);

    @FormUrlEncoded
    @POST(constants.FAVORITE + constants.GET)
    Call<ProductArrayListResponse> getFavorite(@Field("id") String id,
                                               @Field("next") String next);

}
