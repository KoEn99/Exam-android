package com.koen.exam.views.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.koen.exam.R;

public class SheetCreateExam extends BottomSheetDialogFragment implements View.OnClickListener {
    Button createExamBtn;
    TextInputEditText editExamName, editExamDesc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_create_exam, container, false);
        createExamBtn = view.findViewById(R.id.postCreateExam);
        editExamName = view.findViewById(R.id.editTextNameExam);
        editExamDesc = view.findViewById(R.id.editTextDescriptionExam);
        createExamBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.postCreateExam){

        }
    }
}
