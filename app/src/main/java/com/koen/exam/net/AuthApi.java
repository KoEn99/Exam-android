package com.koen.exam.net;

import com.koen.exam.model.AnswerResponse;
import com.koen.exam.model.AuthDto;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.Token;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/login")
    Call<GenericResponse<Token>> getTokenAuth(@Header("Authorization") String authBasic);
    @POST("/register")
    Call<GenericResponse<AnswerResponse>> createUser(@Body AuthDto authDto);
}
