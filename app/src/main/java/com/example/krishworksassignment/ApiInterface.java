package com.example.krishworksassignment;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("v4/spreadsheets/1PN6Dc31IMzJCDtORD3DftwAEGRujfT-TClIuCa79h2M/values/Data!A1:C7?key=AIzaSyCjOGp6A0bmvUuIWztRoUCRsVms88mkYac")
    Call<SheetResponse> getAPI();
}
