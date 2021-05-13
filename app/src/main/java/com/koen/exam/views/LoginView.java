package com.koen.exam.views;

import com.koen.exam.model.Token;

public interface LoginView extends GlobalView {
    void saveJwtToken(Token data);
    void selectActivity();
}
