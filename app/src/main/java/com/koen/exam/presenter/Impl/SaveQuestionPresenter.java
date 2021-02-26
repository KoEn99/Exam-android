package com.koen.exam.presenter.Impl;

import com.koen.exam.model.AnswerData;
import com.koen.exam.model.QuestionData;
import com.koen.exam.model.SaveQuestionModel;
import com.koen.exam.views.SaveQuestionMethods;

public class SaveQuestionPresenter implements SaveQuestionMethods.Presenter {

    SaveQuestionMethods.View view;
    SaveQuestionMethods.Model model;

    public SaveQuestionPresenter(SaveQuestionMethods.View view){
        this.view = view;
        model = new SaveQuestionModel(this);
    }

    @Override
    public void postSaveQuestion(QuestionData questionData) {
        model.saveQuestion(questionData);
    }

    @Override
    public void onSuccessRequest() {
        view.onSuccess();
    }

    @Override
    public void onFailRequest() {
        view.onFailure();
    }
}
