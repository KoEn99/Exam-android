package com.koen.exam.views;

import com.koen.exam.model.ExamModel;
import com.koen.exam.model.GenericResponse;

public interface CreateExamMethods {

    interface View{
        void onSuccess(GenericResponse<ExamModel> model);
        void onFail();
    }
    interface Model{
        void createExam(ExamModel modelExam, String token);

    }
    interface Presenter{
        void postCreateExam(ExamModel modelExam, String token);
        void onSuccessResponse(GenericResponse<ExamModel> model);
        void onFailResponse();
    }
}
