package com.koen.exam.presenter.Impl;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.QuestionData;
import com.koen.exam.service.Impl.PassingTestFragmentModel;
import com.koen.exam.views.PassingTestFragmentMethods;

import java.util.List;

public class PassingTestFragmentPresenter implements PassingTestFragmentMethods.Presenter {
    PassingTestFragmentMethods.View view;
    PassingTestFragmentMethods.Model model;
    public PassingTestFragmentPresenter(PassingTestFragmentMethods.View view){
        this.view = view;
        this.model = new PassingTestFragmentModel(this);
    }

    @Override
    public void onSuccess(GenericResponse<List<QuestionData>> questions) {
        view.onSuccessLoadQuestions(questions);
    }

    @Override
    public void onFail() {
        view.onFailLoadQuestions();
    }

    @Override
    public void postRequestGetQuestionsExam(Long id) {
        model.getExamQuestions(id);
    }
}
