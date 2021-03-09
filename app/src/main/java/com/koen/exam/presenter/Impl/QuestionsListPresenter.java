package com.koen.exam.presenter.Impl;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.QuestionData;
import com.koen.exam.service.Impl.QuestionsModel;
import com.koen.exam.views.FragmentCreateQuestionsMethods;

import java.util.List;

public class QuestionsListPresenter implements FragmentCreateQuestionsMethods.Presenter {
    private FragmentCreateQuestionsMethods.View view;
    private FragmentCreateQuestionsMethods.Model model;
    public QuestionsListPresenter(FragmentCreateQuestionsMethods.View view){
        this.view = view;
        model = new QuestionsModel(this);
    }
    @Override
    public void postGetQuestions(long id) {
        model.postGetMyQuestions(id);
    }

    @Override
    public void onSuccessGetQuestions(GenericResponse<List<QuestionData>> data) {
        view.onSuccessResponse(data);
    }

    @Override
    public void onFailGetQuestions() {
        view.onFailResponse();
    }
}
