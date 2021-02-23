package com.koen.exam.views;

import com.koen.exam.model.ExamModel;

public interface CreateExamMethods {

    interface View{
        void onSuccess();
        void onFail();
    }
    interface Model{
        void createExam(ExamModel modelExam, String token);

    }
    interface Presenter{
        void postCreateExam(ExamModel modelExam, String token);
        void onSuccessResponse();
        void onFailResponse();
    }
}
