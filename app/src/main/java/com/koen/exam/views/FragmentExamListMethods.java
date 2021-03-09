package com.koen.exam.views;

import com.koen.exam.model.ExamModel;
import com.koen.exam.model.GenericResponse;

import java.util.List;

public interface FragmentExamListMethods {
    interface View{
        void onSuccessGetExams(GenericResponse<List<ExamModel>> model);
        void onFailureGetExams();
    }
    interface Presenter{
        void onSuccess(GenericResponse<List<ExamModel>> model);
        void onFail();
        void postGetExams(String id);
    }
    interface Model{
        void getExams(String id);
    }
}
