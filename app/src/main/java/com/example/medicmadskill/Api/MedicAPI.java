package com.example.medicmadskill.Api;

import com.example.medicmadskill.Models.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MedicAPI {

    @POST("sendCode")
    Call<Void> sendCode(@Header("email") String email);

    @POST("api/signin")
    Call<User> signIn(@Header("email") String email, @Header("code") Integer code);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://medic.madskill.ru/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}