package com.koen.exam.views;

import com.koen.exam.model.AnswerData;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionData;

import java.util.List;

public interface FragmentAnswersMethods {
    interface View{
        void onSuccess();
        void onSuccessLoadAnswers(GenericResponse<QuestionData> answers);
        void onFailLoadAnswers(GenericResponse<QuestionData> answers);
        void onFailure();
    }
    interface Presenter{
        void postGetAnswers(long id);
        void postSaveQuestion(QuestionData questionData);
        void onSuccessRequestAnswers(GenericResponse<QuestionData> answers);
        void onFailRequestAnswers(GenericResponse<QuestionData> answers);
        void onSuccessRequest();
        void onFailRequest();
    }
    interface Model{
        void loadAnswers(long id);
        void saveQuestion(QuestionData questionData);
    }
}
