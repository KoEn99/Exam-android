package com.koen.exam.model;

public class OneAnsInfo {
    private String answer;
    private Boolean answerCorrect;

    public OneAnsInfo(String answer, Boolean trueAns) {
        this.answer = answer;
        this.answerCorrect = trueAns;
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
