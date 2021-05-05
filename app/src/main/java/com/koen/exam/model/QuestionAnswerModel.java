package com.koen.exam.model;

import java.util.List;

public class QuestionAnswerModel {
    private Long id;
    private List<AnswersToSendData> answers;
    private String questionType;

    public QuestionAnswerModel(Long id, List<AnswersToSendData> answers, String questionType) {
        this.id = id;
        this.answers = answers;
        this.questionType = questionType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AnswersToSendData> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersToSendData> answers) {
        this.answers = answers;
    }
}
