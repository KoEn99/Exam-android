package com.koen.exam.views;

import com.koen.exam.model.QuestionAnswerModel;
import com.koen.exam.model.ScoreModel;
import com.koen.exam.model.SendQuestionAnswersModel;

import java.util.List;

public interface FragmentEndTestMethods {
    interface View{
        void onSuccessSendResults(ScoreModel score);
        void onFailSendResults();
    }
    interface Presenter{
        void onSuccess(ScoreModel score);
        void sendRequestResults(SendQuestionAnswersModel sendQuestionAnswersModel);
        void onFail();

    }
    interface Model{
        void sendRequestResults(SendQuestionAnswersModel sendQuestionAnswersModel);
    }
}
