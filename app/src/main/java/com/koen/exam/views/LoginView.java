package com.koen.exam.views;

public interface LoginView extends GlobalView {
    void saveJwtToken(String jwtToken);
    void selectActivity();
}
