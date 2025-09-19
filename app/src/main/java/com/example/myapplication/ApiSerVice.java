package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiSerVice {
    @GET("posts")//Endpoint
    Call<List< TextNote>> getTextNote();
}
