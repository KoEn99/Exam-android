package com.koen.exam.views;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.QuestionData;

import java.util.List;

public interface FragmentCreateQuestionsMethods {
    interface View{
        void onSuccessResponse(GenericResponse<List<QuestionData>> data);
        void onFailResponse();
    }
    interface Presenter{
        void postGetQuestions(long id);
        void onSuccessGetQuestions(GenericResponse<List<QuestionData>> data);
        void onFailGetQuestions();
    }
    interface Model{
        void postGetMyQuestions(long id);
    }
}
