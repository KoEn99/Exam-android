package com.koen.exam.views.Impl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.R;
import com.koen.exam.views.CreateExamMethods;
import com.koen.exam.views.dialogs.SheetCreateExam;

public class ExamListFragment extends Fragment implements CreateExamMethods.View {
    FloatingActionButton floatingActionButton;
    String courseId;

    public ExamListFragment(String courseId){
        this.courseId = courseId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_list, container, false);
        NavigationActivity navigationActivity = (NavigationActivity)getActivity();
        navigationActivity.visibleArrow();
        navigationActivity.getSupportActionBar().setTitle("Редактирование курса");
        floatingActionButton = (FloatingActionButton)navigationActivity.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SheetCreateExam sheetCreateExam = new SheetCreateExam(ExamListFragment.this,courseId);
                sheetCreateExam.show(getFragmentManager(),"examCreateSheet");
            }
        });
        return view;
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getContext(),"Тест успешно создан",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFail() {
        Toast.makeText(getContext(),"Ошибка при создании теста",Toast.LENGTH_SHORT).show();
    }
}