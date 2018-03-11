package com.example.roy1473.recyclerviewexample;

import com.example.roy1473.recyclerviewexample.Interface.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Roy1473 on 2018/03/05.
 */

public class InternetManager {

    private ApiInterface service;

    public ApiInterface getApiInterface(){

        /**
         * The Retrofit class generates an implementation of the UService interface.
         */
        if(service == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiInterface.END_POINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            service = retrofit.create(ApiInterface.class);
        }
        return service;
    }
}
