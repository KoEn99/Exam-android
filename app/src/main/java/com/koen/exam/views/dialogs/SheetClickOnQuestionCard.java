package com.koen.exam.views.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.koen.exam.R;
import com.koen.exam.model.QuestionData;
import com.koen.exam.views.Impl.FragmentOneAns;

public class SheetClickOnQuestionCard extends BottomSheetDialogFragment {
    LinearLayout editQuestion, deleteQuestion;
    int idExam;
    String questionType;
    QuestionData questionData;

    public SheetClickOnQuestionCard(int idExam, String questionType, QuestionData questionData){
        this.idExam = idExam;
        this.questionType = questionType;
        this.questionData = questionData;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_long_click_on_question_card,container,false);
        editQuestion = view.findViewById(R.id.sheetEditQuestion);
        deleteQuestion = view.findViewById(R.id.sheetRemoveQuestion);
        editQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(questionType.equals("SINGLE")){
                   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.scrim,
                           new FragmentOneAns(1,idExam,questionData.getId())).addToBackStack(null).commit();
                   SheetClickOnQuestionCard.this.dismiss();
               }
               else if(questionType.equals("MULTIPLE")){
                   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.scrim,
                           new FragmentOneAns(2,idExam,questionData.getId())).addToBackStack(null).commit();
                   SheetClickOnQuestionCard.this.dismiss();
               }
            }
        });
        deleteQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;

    }
}
