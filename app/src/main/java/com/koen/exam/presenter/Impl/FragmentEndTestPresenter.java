package com.koen.exam.presenter.Impl;

import com.koen.exam.model.QuestionAnswerModel;
import com.koen.exam.model.ScoreModel;
import com.koen.exam.model.SendQuestionAnswersModel;
import com.koen.exam.service.Impl.FragmentEndTestModel;
import com.koen.exam.views.FragmentEndTestMethods;

import java.util.List;

public class FragmentEndTestPresenter implements FragmentEndTestMethods.Presenter {
    FragmentEndTestMethods.View view;
    FragmentEndTestMethods.Model model;
    public FragmentEndTestPresenter(FragmentEndTestMethods.View view){
        this.view = view;
        this.model = new FragmentEndTestModel(this);
    }

    @Override
    public void onSuccess(ScoreModel score) {
        view.onSuccessSendResults(score);
    }

    @Override
    public void sendRequestResults(SendQuestionAnswersModel sendQuestionAnswersModel) {
        model.sendRequestResults(sendQuestionAnswersModel);
    }

    @Override
    public void onFail() {
        view.onFailSendResults();
    }
}
