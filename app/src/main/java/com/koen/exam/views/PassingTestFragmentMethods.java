package com.koen.exam.views;

import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.QuestionData;

import java.util.List;

public interface PassingTestFragmentMethods {
    interface View{
        void onSuccessLoadQuestions(GenericResponse<List<QuestionData>> questions);
        void onFailLoadQuestions();
    }
    interface Presenter{
        void onSuccess(GenericResponse<List<QuestionData>> questions);
        void onFail();
        void postRequestGetQuestionsExam(Long id);
    }
    interface Model{
        void getExamQuestions(Long id);
    }
}
