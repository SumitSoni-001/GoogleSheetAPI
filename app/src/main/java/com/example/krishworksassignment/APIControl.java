package com.example.krishworksassignment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIControl {

    Retrofit RETROFIT = null;

    public Retrofit getAPI() {
        if (RETROFIT == null) {
            RETROFIT = new retrofit2.Retrofit.Builder()
                    .baseUrl("https://sheets.googleapis.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return RETROFIT;
    }
}