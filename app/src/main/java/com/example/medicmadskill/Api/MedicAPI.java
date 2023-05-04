package com.example.medicmadskill.Api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MedicAPI {
    @POST("api/sendCode")
    Call<Medic>

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://virtserver.swaggerhub.com/serk87/APIfood/FRBHWRIOJAFIDSNKJF")
            .addConverterFactory(GsonConverterFactory.create())
            .build();



}
