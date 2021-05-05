package com.koen.exam.views.dialogs;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.koen.exam.DataSingleton;
import com.koen.exam.R;
import com.koen.exam.model.ExamModel;
import com.koen.exam.model.Token;
import com.koen.exam.presenter.Impl.ExamPresenterImpl;
import com.koen.exam.views.CreateExamMethods;

public class SheetCreateExam extends BottomSheetDialogFragment implements View.OnClickListener {
    Button createExamBtn;
    TextInputEditText editExamName, editExamDesc;
    CreateExamMethods.Presenter presenter;
    String courseId;

    public SheetCreateExam(CreateExamMethods.View view, String courseId){
        this.presenter = new ExamPresenterImpl(view);
        this.courseId = courseId;
    }

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
            presenter.postCreateExam(new ExamModel(editExamName.getText().toString(),
                    editExamDesc.getText().toString(),
                    0,
                    "ACTIVE",
                    courseId), DataSingleton.getInstance().jwtToken);
        }
    }
}
