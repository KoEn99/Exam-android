package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.R;
import com.koen.exam.model.OneAnsInfo;
import com.koen.exam.model.QuestionData;
import com.koen.exam.presenter.Impl.SaveQuestionPresenter;
import com.koen.exam.views.CreateExamMethods;
import com.koen.exam.views.CreateQuestionsMethods;
import com.koen.exam.views.SaveQuestionMethods;

import java.util.ArrayList;
import java.util.List;

public class FragmentFreeAns extends Fragment implements SaveQuestionMethods.View {
    EditText questionTxt, answerTxt;
    SaveQuestionPresenter presenter;
    NavigationActivity navigationActivity;
    List<OneAnsInfo> listAns;
    int examId;

    public FragmentFreeAns(int examId){
        this.examId = examId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        navigationActivity.setVisibleSaveBtn(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listAns = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_free_answer,container,false);
        questionTxt = view.findViewById(R.id.questionFreeTxt);
        answerTxt = view.findViewById(R.id.answerFreeTxt);
        presenter = new SaveQuestionPresenter(this);
        navigationActivity = (NavigationActivity) getActivity();
        FloatingActionButton fab = navigationActivity.findViewById(R.id.fab);
        fab.hide();
        return view;
    }


    @Override
    public void onSuccess() {
        Toast.makeText(getActivity(),"Вопрос сохранен",Toast.LENGTH_SHORT).show();
        getFragmentManager().beginTransaction().replace(R.id.scrim,new FragmentCreateQuestions(examId,null)).commit();
    }

    @Override
    public void onFailure() {
        Toast.makeText(getActivity(),"Ошибка при создании вопроса",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String type = "FREE_ANSWER";

        if(item.getItemId() == R.id.save_ques){
            listAns.add(new OneAnsInfo(answerTxt.getText().toString(),true));
            presenter.postSaveQuestion(new QuestionData(questionTxt.getText().toString(),
                    type,
                    (float)1.0,
                    listAns,
                    examId
            ));
        }
        return true;
    }
}
