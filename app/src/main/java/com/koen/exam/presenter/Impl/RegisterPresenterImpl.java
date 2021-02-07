package com.koen.exam.presenter.Impl;

import com.koen.exam.model.AnswerResponse;
import com.koen.exam.model.AuthDto;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.presenter.RegisterPresenter;
import com.koen.exam.service.Impl.RegisterServiceImpl;
import com.koen.exam.service.RegisterService;
import com.koen.exam.views.RegisterView;

public class RegisterPresenterImpl implements RegisterPresenter {
    RegisterService registerService;
    RegisterView registerView;
    public RegisterPresenterImpl(RegisterView registerView){
        this.registerView = registerView;
        registerService = new RegisterServiceImpl(this);
    }
    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void getMessageAnswer(String messageAnswer) {
        registerView.createToast(messageAnswer);
    }

    @Override
    public void listenerFinishError(String message) {

    }

    @Override
    public void listenerFinish(GenericResponse<?> finishData) {
        AnswerResponse answerResponse = (AnswerResponse)finishData.getResponseData();
        getMessageAnswer(answerResponse.getAnswerResponseMap().get("answer"));
    }

    @Override
    public void registerUser(AuthDto authDto) {
        registerService.createUser(authDto);
    }
}
