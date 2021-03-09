package com.koen.exam.views.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.koen.exam.R;
import com.koen.exam.views.Impl.FragmentFreeAns;
import com.koen.exam.views.Impl.FragmentOneAns;

public class SheetCreateQuestion extends BottomSheetDialogFragment implements View.OnClickListener {
    LinearLayout oneAnsBtn, manyAnsBtn, freeAnsBtn;
    String groupId;
    int idExam;

    public SheetCreateQuestion(String groupId, int idExam){
        this.groupId = groupId;
        this.idExam = idExam;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_create_question,container,false);
        oneAnsBtn = view.findViewById(R.id.oneAnswer);
        manyAnsBtn = view.findViewById(R.id.manyAns);
        freeAnsBtn = view.findViewById(R.id.freeAns);
        oneAnsBtn.setOnClickListener(this);
        manyAnsBtn.setOnClickListener(this);
        freeAnsBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.oneAnswer:{
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("CreateQuestionsFragment").replace(R.id.scrim,new FragmentOneAns(1,idExam,-1)).commit();
                break;
            }
            case R.id.manyAns:{
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("CreateQuestionsFragment").replace(R.id.scrim,new FragmentOneAns(2,idExam,-1)).commit();
                break;
            }
            case R.id.freeAns:{
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("CreateQuestionsFragment").replace(R.id.scrim,new FragmentFreeAns(3)).commit();
                break;
            }
        }

    }
}
