package com.koen.exam.presenter.Impl;


import android.util.Base64;

import com.koen.exam.model.AuthDto;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.Token;
import com.koen.exam.presenter.AuthPresenter;
import com.koen.exam.service.AuthService;
import com.koen.exam.service.Impl.AuthServiceImpl;
import com.koen.exam.views.LoginView;

public class AuthPresenterImpl implements AuthPresenter {
    LoginView loginView;
    AuthService authService;
    public AuthPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        authService = new AuthServiceImpl(this);
    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void getMessageAnswer(String messageAnswer) {
        loginView.createToast(messageAnswer);
    }

    @Override
    public void listenerFinishError(String message) {
        int indexStart = message.indexOf("errorMessage") + 14;
        int indexFinish = message.indexOf("responseData") - 2;
        String resultsMessage = message.substring(indexStart, indexFinish);
        getMessageAnswer(resultsMessage);
    }

    @Override
    public void listenerFinish(GenericResponse<?> finishData) {
        Token token = (Token)finishData.getResponseData();
        getMessageAnswer("Авторизация прошла успешно");
        loginView.saveJwtToken(token.getAuthToken());
        loginView.selectActivity();
    }

    @Override
    public void loginUser(String authorization) {
        String authorizationCode = "Basic " + convertToBase64(authorization);
        authService.getTokenAuth(authorizationCode);
    }

    private String convertToBase64(String clientCredentials) {
        byte[] bytes = clientCredentials.getBytes();
        return new String(android.util.Base64.encode(bytes, Base64.NO_WRAP));
    }
}
