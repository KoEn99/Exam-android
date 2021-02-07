package com.koen.exam.service;

import com.koen.exam.model.AuthDto;

public interface RegisterService {
    void createUser(AuthDto authDto);
}
