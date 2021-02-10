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
import com.koen.exam.model.CourseInfo;
import com.koen.exam.presenter.CoursesPresenter;
import com.koen.exam.presenter.Impl.CoursesPresenterImpl;
import com.koen.exam.views.CoursesView;

import java.util.Objects;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SheetsCreateGroup extends BottomSheetDialogFragment {
    Button buttonCreateCourse;
    TextInputEditText nameEditText, descEditText;
    CoursesPresenter coursesPresenter;
    SheetsCreateGroup sheetsCreateGroup;
    public SheetsCreateGroup(CoursesView coursesView) {
        this.coursesPresenter = new CoursesPresenterImpl(coursesView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_course_sheet, container, false);
        buttonCreateCourse = (Button)view.findViewById(R.id.postCreateCourse);
        nameEditText = (TextInputEditText)view.findViewById(R.id.editTextNameCourse);
        descEditText = (TextInputEditText)view.findViewById(R.id.editTextDescriptionCourse);
        buttonCreateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coursesPresenter.createMyCourse(
                        new CourseInfo("", Objects.requireNonNull(nameEditText.getText()).toString(),
                                descEditText.getText().toString(), null)
                );
                SheetsCreateGroup.this.dismiss();
            }
        });
        return view;
    }
}
