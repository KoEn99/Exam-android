package com.koen.exam.views.Impl;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koen.exam.R;

import java.util.Objects;

public class ExamListFragment extends Fragment {
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
        navigationActivity.floatingActionButton.hide();
        return view;
    }
}