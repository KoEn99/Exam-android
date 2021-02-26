package com.koen.exam.presenter.Impl;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.service.Impl.ExamModel;
import com.koen.exam.views.CreateExamMethods;

public class ExamPresenterImpl implements CreateExamMethods.Presenter {
    CreateExamMethods.View view;
    CreateExamMethods.Model model;

    public ExamPresenterImpl(CreateExamMethods.View view){
        this.view = view;
        model = new ExamModel(this);
    }

    @Override
    public void postCreateExam(com.koen.exam.model.ExamModel modelExam, String token) {
        model.createExam(modelExam,token);
    }

    @Override
    public void onSuccessResponse(GenericResponse<com.koen.exam.model.ExamModel> model) {
        view.onSuccess(model);
    }

    @Override
    public void onFailResponse() {
        view.onFail();
    }
}
