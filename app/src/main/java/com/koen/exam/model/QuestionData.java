package com.koen.exam.model;

import java.util.List;

public class QuestionData {
    private String question;
    private String questionType;
    private Float questionScore;
    private Integer examId;
    private List<OneAnsInfo> answers;

    public QuestionData(String question, String questionType, Float questionScore, List<OneAnsInfo> answers, Integer id) {
        this.question = question;
        this.questionType = questionType;
        this.questionScore = questionScore;
        this.answers = answers;
        this.examId = id;
    }

    public Integer getId() {
        return examId;
    }

    public void setId(Integer id) {
        this.examId = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Float getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Float questionScore) {
        this.questionScore = questionScore;
    }

    public List<OneAnsInfo> getAnswers() {
        return answers;
    }

    public void setAnswers(List<OneAnsInfo> answers) {
        this.answers = answers;
    }
}
