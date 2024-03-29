package com.koen.exam.model;

public class AnswerData {
    private String answer;
    private Boolean answerCorrect;
    private Long id;

    public AnswerData(String answer, Boolean answerCorrect) {
        this.answer = answer;
        this.answerCorrect = answerCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(Boolean answerCorrect) {
        this.answerCorrect = answerCorrect;
    }
}
