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
import com.koen.exam.model.GroupInfo;
import com.koen.exam.presenter.GroupPresenter;
import com.koen.exam.presenter.Impl.GroupPresenterImpl;
import com.koen.exam.views.GroupView;

public class SheetCreateGroup extends BottomSheetDialogFragment {
    View view;
    Button button;
    TextInputEditText nameEditText;
    String courseId;
    GroupPresenter groupPresenter;
    public SheetCreateGroup(GroupView groupView, String courseId){
        this.courseId = courseId;
        this.groupPresenter = new GroupPresenterImpl(groupView);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.create_group_sheet, container, false);
        nameEditText = (TextInputEditText)view.findViewById(R.id.nameGroupEdiText);
        button = (Button)view.findViewById(R.id.postCreateGroup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupInfo groupInfo = new GroupInfo();
                CourseInfo courseInfo = new CourseInfo();
                courseInfo.setId(courseId);
                groupInfo.setName(nameEditText.getText().toString());
                groupInfo.setCoursesEntity(courseInfo);
                groupPresenter.addGroup(groupInfo);
                SheetCreateGroup.this.dismiss();
            }
        });
        return view;
    }
}
