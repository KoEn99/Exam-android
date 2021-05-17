package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.AnswersToSendData;
import com.koen.exam.model.QuestionAnswerModel;
import com.koen.exam.model.ScoreModel;
import com.koen.exam.model.SendQuestionAnswersModel;
import com.koen.exam.model.TypeQuestion;
import com.koen.exam.presenter.Impl.FragmentEndTestPresenter;
import com.koen.exam.views.FragmentEndTestMethods;

import java.util.ArrayList;
import java.util.List;

public class FragmentEndTest extends Fragment implements View.OnClickListener, FragmentEndTestMethods.View {
    List<QuestionAnswerModel> listAnswers = new ArrayList<>();
    List<AnswersToSendData> answers = new ArrayList<>();
    Button endTestBtn;
    FragmentEndTestPresenter endTestPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_end_test,container,false);
        endTestBtn = v.findViewById(R.id.endTestBtn);
        endTestBtn.setOnClickListener(this);
        endTestPresenter = new FragmentEndTestPresenter(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.endTestBtn){
            for(int i=0;i< DataSingleton.getInstance().questionDataList.size();i++){
                if(DataSingleton.getInstance().questionDataList.get(i).getId() == null){
                    break;
                }
                long idQues = DataSingleton.getInstance().questionDataList.get(i).getId();
                if(!DataSingleton.getInstance().questionDataList.get(i).getQuestionType().equals(TypeQuestion.FREE_ANSWER.toString())) {
                    for (int j = 0; j < DataSingleton.getInstance().questionDataList.get(i).getAnswers().size(); j++) {
                        if (DataSingleton.getInstance().questionDataList.get(i).getAnswers().get(j).getTrueAns()) {
                            answers.add(new AnswersToSendData(DataSingleton.getInstance().
                                    questionDataList.get(i).
                                    getAnswers().get(j).
                                    getId(), DataSingleton
                                    .getInstance().
                                            questionDataList.get(i)
                                    .getAnswers().get(j).getAnswer()));
                        }
                    }
                    listAnswers.add(new QuestionAnswerModel(idQues, answers, DataSingleton.
                            getInstance().
                            questionDataList.get(i).getQuestionType()));
                    answers = new ArrayList<>();
                }
                else{
                    answers.add(new AnswersToSendData(null,DataSingleton.getInstance().questionDataList.get(i).getAnswers().get(0).getAnswer()));
                    listAnswers.add(new QuestionAnswerModel(idQues,answers,DataSingleton.getInstance().questionDataList.get(i).getQuestionType()));
                    answers = new ArrayList<>();
                }
            }
            SendQuestionAnswersModel sendQuestionAnswersModel = new SendQuestionAnswersModel(listAnswers);
            endTestPresenter.sendRequestResults(sendQuestionAnswersModel);

        }
    }

    @Override
    public void onSuccessSendResults(ScoreModel score) {
        DataSingleton.getInstance().questionDataList = null;
        getFragmentManager().beginTransaction().replace(R.id.scrim,new FragmentTestResults(score)).addToBackStack("passingTest").commit();
    }

    @Override
    public void onFailSendResults() {
        Toast.makeText(getActivity(),"Ошибка отправки результата",Toast.LENGTH_SHORT).show();
    }
}
