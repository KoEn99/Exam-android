package com.koen.exam.model;

public class OneAnsInfo {
    private String answer;
    private Boolean answerCorrect;
    private Long id;

    public OneAnsInfo(String answer, Boolean trueAns) {
        this.answer = answer;
        this.answerCorrect = trueAns;
    }

    public Boolean getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(Boolean answerCorrect) {
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

    public Boolean getTrueAns() {
        return answerCorrect;
    }

    public void setTrueAns(Boolean trueAns) {
        this.answerCorrect = trueAns;
    }
}
