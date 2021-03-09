package com.koen.exam.presenter.Impl;

import com.koen.exam.service.Impl.ExamListModel;
import com.koen.exam.model.ExamModel;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.views.FragmentExamListMethods;

import java.util.List;

public class ExamListPresenter implements FragmentExamListMethods.Presenter {
    FragmentExamListMethods.View view;
    FragmentExamListMethods.Model model;
    public ExamListPresenter(FragmentExamListMethods.View view){
        this.view = view;
        model = new ExamListModel(this);
    }

    @Override
    public void onSuccess(GenericResponse<List<ExamModel>> model) {
        view.onSuccessGetExams(model);
    }

    @Override
    public void onFail() {
        view.onFailureGetExams();
    }

    @Override
    public void postGetExams(String id) {
        model.getExams(id);
    }
}
