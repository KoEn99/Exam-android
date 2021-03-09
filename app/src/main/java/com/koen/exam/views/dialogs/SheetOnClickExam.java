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
import com.koen.exam.views.Impl.FragmentCreateQuestions;

public class SheetOnClickExam extends BottomSheetDialogFragment {
    private Integer idExam;
    private LinearLayout deleteExam, editExam;
    public SheetOnClickExam(int idExam){
        this.idExam = idExam;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_on_click_exam_card,container,false);
        deleteExam = view.findViewById(R.id.sheetRemoveExam);
        editExam = view.findViewById(R.id.sheetEditExam);

        deleteExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.scrim, new FragmentCreateQuestions(idExam,null))
                        .addToBackStack(null)
                        .commit();
                SheetOnClickExam.this.dismiss();
            }
        });

        return view;
    }
}
