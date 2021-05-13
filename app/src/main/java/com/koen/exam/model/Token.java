package com.koen.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Token {
    private String authToken;
    private String refreshToken;
    private String fio;
    private String email;
}

