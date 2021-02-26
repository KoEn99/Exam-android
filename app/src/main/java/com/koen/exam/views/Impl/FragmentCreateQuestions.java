package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.R;
import com.koen.exam.views.dialogs.SheetCreateQuestion;

public class FragmentCreateQuestions extends Fragment implements View.OnClickListener{
    FloatingActionButton fab;
    String groupId;
    int idExam;
    NavigationActivity navigationActivity;
    public FragmentCreateQuestions(int idExam, String groupId){
        this.idExam = idExam;
        this.groupId = groupId;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_questions,container,false);
        navigationActivity = (NavigationActivity)getActivity();
        navigationActivity.setTitle("Создание вопросов");
        fab = navigationActivity.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){
            SheetCreateQuestion sheetCreateQuestion = new SheetCreateQuestion(groupId, idExam);
            sheetCreateQuestion.show(navigationActivity.getSupportFragmentManager(),"createQuesTag");
            navigationActivity.backgroundNavigation.setVisibility(View.INVISIBLE);
        }
    }
}
