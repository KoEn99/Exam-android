package com.koen.exam.views;

import com.koen.exam.model.SubCoursesModel;

import java.util.List;

public interface SubsExamsFragmentMethods {
    interface View{
        void onSuccessLoadExams(SubCoursesModel subCoursesModel);
        void onFailLoadExams();
    }
    interface Presenter{
        void onSuccess(SubCoursesModel subCoursesModel);
        void postRequestSubsExams(String id);
        void onFail();
    }
    interface Model{
        void getSubsExam(String id);
    }
}
