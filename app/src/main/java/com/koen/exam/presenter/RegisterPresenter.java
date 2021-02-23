package com.koen.exam.presenter;

import com.koen.exam.model.AuthDto;

public interface RegisterPresenter extends GlobalPresenter {
    void registerUser(AuthDto authDto);
    void onFail();
    void onSuccess();
}
