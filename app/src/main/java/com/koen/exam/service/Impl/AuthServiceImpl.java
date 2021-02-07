package com.koen.exam.service.Impl;

import android.util.Base64;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.Token;
import com.koen.exam.net.NetworkService;
import com.koen.exam.presenter.AuthPresenter;
import com.koen.exam.service.AuthService;

import java.util.Arrays;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthServiceImpl implements AuthService {

    AuthPresenter authPresenter;
    public AuthServiceImpl(AuthPresenter authPresenter){
        this.authPresenter = authPresenter;
    }
    @Override
    public void getTokenAuth(String authorizationCode) {
        NetworkService.getInstance()
                .getJSONApi()
                .getTokenAuth(authorizationCode)
                .enqueue(new Callback<GenericResponse<Token>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<GenericResponse<Token>> call, Response<GenericResponse<Token>> response) {
                        if (response.isSuccessful()) authPresenter.listenerFinish(response.body());
                        else authPresenter.listenerFinishError(response.errorBody() != null ? response.errorBody().string() : null);
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<Token>> call, Throwable t) {
                    }
                });
    }
}
