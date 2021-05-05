package com.koen.exam.model;

import java.util.List;

public class SendQuestionAnswersModel {
    List<QuestionAnswerModel> questionAnswerDto;

    public SendQuestionAnswersModel(List<QuestionAnswerModel> questionAnswerDto) {
        this.questionAnswerDto = questionAnswerDto;
    }

    public List<QuestionAnswerModel> getQuestionAnswerDto() {
        return questionAnswerDto;
    }

    public void setQuestionAnswerDto(List<QuestionAnswerModel> questionAnswerDto) {
        this.questionAnswerDto = questionAnswerDto;
    }
}
