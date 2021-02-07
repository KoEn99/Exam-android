package com.koen.exam.presenter;

import com.koen.exam.model.AuthDto;

public interface AuthPresenter extends GlobalPresenter {
    void loginUser(String authorization);
}
