package com.koen.exam.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class AnswerResponse {
    Map<String, String> answerResponseMap;
    public AnswerResponse(String answer){
        this.answerResponseMap = new HashMap<>();
        answerResponseMap.put("answer", answer);
    }
}