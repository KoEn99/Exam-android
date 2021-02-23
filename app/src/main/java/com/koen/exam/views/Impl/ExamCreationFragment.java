package com.koen.exam.views.Impl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.R;

public class ExamCreationFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam_creation, container, false);
        NavigationActivity navigationActivity = (NavigationActivity)getActivity();
        navigationActivity.getSupportActionBar().setTitle("Создание теста");

        FloatingActionButton floatingActionButton = navigationActivity.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab){

        }

    }
}