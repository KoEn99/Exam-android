package com.koen.exam.presenter.Impl;

import com.koen.exam.model.SubCoursesModel;
import com.koen.exam.service.Impl.SubsExamsFragmentModel;
import com.koen.exam.views.SubsExamsFragmentMethods;

import java.util.List;

public class SubsExamsFragmentPresenter implements SubsExamsFragmentMethods.Presenter {
    SubsExamsFragmentMethods.View view;
    SubsExamsFragmentMethods.Model model;
    public SubsExamsFragmentPresenter(SubsExamsFragmentMethods.View view){
        this.view = view;
        this.model = new SubsExamsFragmentModel(this);
    }
    @Override
    public void onSuccess(SubCoursesModel subCoursesModel) {
        view.onSuccessLoadExams(subCoursesModel);
    }

    @Override
    public void postRequestSubsExams(String id) {
        model.getSubsExam(id);
    }

    @Override
    public void onFail() {
        view.onFailLoadExams();
    }
}
