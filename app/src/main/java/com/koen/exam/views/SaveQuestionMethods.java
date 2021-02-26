package com.koen.exam.views;

import com.koen.exam.model.AnswerData;
import com.koen.exam.model.QuestionData;

public interface SaveQuestionMethods {
    interface View{
        void onSuccess();
        void onFailure();
    }
    interface Presenter{
        void postSaveQuestion(QuestionData questionData);
        void onSuccessRequest();
        void onFailRequest();
    }
    interface Model{
        void saveQuestion(QuestionData questionData);
    }
}
