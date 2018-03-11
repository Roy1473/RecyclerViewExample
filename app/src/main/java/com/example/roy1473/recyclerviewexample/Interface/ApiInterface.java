package com.example.roy1473.recyclerviewexample.Interface;

import com.example.roy1473.recyclerviewexample.Models.ShopData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Roy1473 on 2018/03/05.
 */

public interface ApiInterface {
    String END_POINT = "http://webservice.recruit.co.jp";
    @GET("/hotpepper/gourmet/v1/")
    Call<ShopData> getShopData(@Query("key") String key, @Query("middle_area") String area,
                               @Query("format") String format);
}
