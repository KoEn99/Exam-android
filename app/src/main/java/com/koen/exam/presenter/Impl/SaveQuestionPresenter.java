package com.koen.exam.presenter.Impl;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionData;
import com.koen.exam.service.Impl.SaveQuestionModel;
import com.koen.exam.views.FragmentAnswersMethods;

import java.util.List;

public class SaveQuestionPresenter implements FragmentAnswersMethods.Presenter {

    FragmentAnswersMethods.View view;
    FragmentAnswersMethods.Model model;

    public SaveQuestionPresenter(FragmentAnswersMethods.View view){
        this.view = view;
        model = new SaveQuestionModel(this);
    }

    @Override
    public void postGetAnswers(long id) {
        model.loadAnswers(id);
    }

    @Override
    public void postSaveQuestion(QuestionData questionData) {
        model.saveQuestion(questionData);
    }

    @Override
    public void onSuccessRequestAnswers(GenericResponse<QuestionData> answers) {
        view.onSuccessLoadAnswers(answers);
    }

    @Override
    public void onFailRequestAnswers(GenericResponse<QuestionData> answers) {
        view.onSuccessLoadAnswers(answers);
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
