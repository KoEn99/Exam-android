package com.koen.exam.model;

public class ResultsOfStudents {
    private String fio;
    private int tryCount;
    private float countCorrectAnswer;
    private int mark;
    private float generalScore;

    public ResultsOfStudents(String fio, int tryCount, float countCorrectAnswer, int mark, float generalScore) {
        this.fio = fio;
        this.tryCount = tryCount;
        this.countCorrectAnswer = countCorrectAnswer;
        this.mark = mark;
        this.generalScore = generalScore;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public float getCountCorrectAnswer() {
        return countCorrectAnswer;
    }

    public void setCountCorrectAnswer(float countCorrectAnswer) {
        this.countCorrectAnswer = countCorrectAnswer;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public float getGeneralScore() {
        return generalScore;
    }

    public void setGeneralScore(float generalScore) {
        this.generalScore = generalScore;
    }
}
