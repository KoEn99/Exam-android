package com.koen.exam.service.Impl;

import com.koen.exam.model.AnswerResponse;
import com.koen.exam.model.AuthDto;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.Token;
import com.koen.exam.net.NetworkService;
import com.koen.exam.presenter.RegisterPresenter;
import com.koen.exam.service.RegisterService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterServiceImpl implements RegisterService {

    RegisterPresenter registerPresenter;

    public RegisterServiceImpl(RegisterPresenter registerPresenter){
        this.registerPresenter = registerPresenter;
    }

    @Override
    public void createUser(AuthDto authDto) {
        NetworkService.getInstance()
                .getJSONApi()
                .createUser(authDto)
                .enqueue(new Callback<GenericResponse<AnswerResponse>>() {
                    @Override
                    public void onResponse(Call<GenericResponse<AnswerResponse>> call, Response<GenericResponse<AnswerResponse>> response) {
                        registerPresenter.listenerFinish(response.body());
                    }

                    @Override
                    public void onFailure(Call<GenericResponse<AnswerResponse>> call, Throwable t) {

                    }
                });
    }
}
