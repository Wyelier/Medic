package com.example.medicmadskill.Api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MedicAPI {

    @POST("api/sendCode")
    Call<Void> sendCode(@Body EmailRequest emailRequest);

    @POST("api/signin")
    Call<SendCodeRequest> signIn(@Body SignInRequest signInRequest);
}
