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
import com.koen.exam.views.Impl.ExamListFragment;
import com.koen.exam.views.Impl.GroupFragment;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SheetClickOnEditRecycle extends BottomSheetDialogFragment {
    String idCourse;
    public SheetClickOnEditRecycle(String idCourse){
        this.idCourse = idCourse;
    }
    LinearLayout editLinear, groupLinear, removeLinear;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sheet_click_on_edit_recycle, container, false);
        editLinear = (LinearLayout)view.findViewById(R.id.sheetEditInEdit);
        groupLinear = (LinearLayout)view.findViewById(R.id.sheetGroupInEdit);
        removeLinear = (LinearLayout)view.findViewById(R.id.sheetRemoveInEdit);
        editLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getActivity().getSupportFragmentManager().beginTransaction().
                            replace(R.id.scrim, new ExamListFragment(idCourse)).commit();
                    SheetClickOnEditRecycle.this.dismiss();
            }
        });
        groupLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.scrim, new GroupFragment(idCourse)).commit();
                SheetClickOnEditRecycle.this.dismiss();
            }
        });
        return view;
    }
}
