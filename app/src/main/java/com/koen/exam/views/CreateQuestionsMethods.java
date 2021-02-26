package com.koen.exam.views;

public interface CreateQuestionsMethods {
    interface View{
        void onSuccess();
        void onFailure();
    }
    interface Presenter{
        void postCreateQuestion();
        void onSuccess();
        void onFailure();
    }
    interface Model{
        void createQuestion();

    }
}
