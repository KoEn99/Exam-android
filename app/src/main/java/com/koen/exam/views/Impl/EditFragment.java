package com.koen.exam.views.Impl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.R;
import com.koen.exam.model.CourseInfo;
import com.koen.exam.presenter.CoursesPresenter;
import com.koen.exam.presenter.Impl.CoursesPresenterImpl;
import com.koen.exam.recycleAdapter.adapter.CourseAdapter;
import com.koen.exam.views.CoursesView;
import com.koen.exam.views.dialogs.SheetsCreateGroup;

import java.util.List;

public class EditFragment extends Fragment implements CoursesView {
    CoursesPresenter coursesPresenter;
    View view;
    RecyclerView recyclerView;
    List<CourseInfo> courseInfoList;
    CourseAdapter courseAdapter;
    FloatingActionButton floatingActionButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleEditView);
        coursesPresenter = new CoursesPresenterImpl(this);
        coursesPresenter.getMyCourses();
        NavigationActivity navigationActivity = (NavigationActivity)getActivity();
        floatingActionButton = (FloatingActionButton)navigationActivity.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationActivity.sheetsCreateGroup = new SheetsCreateGroup(navigationActivity.store);
                navigationActivity.sheetsCreateGroup.show(navigationActivity.getSupportFragmentManager(), "TAG");
                navigationActivity.backgroundNavigation.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    @Override
    public void initialListCoursesAdapter(List<CourseInfo> courseInfoList) {
        this.courseInfoList = courseInfoList;
        courseAdapter = new CourseAdapter(courseInfoList, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(courseAdapter);
    }

    @Override
    public void adapterDataChanger(CourseInfo courseInfo) {
        courseInfoList.add(courseInfo);
        courseAdapter.dataChanged(courseInfoList);
    }

    @Override
    public void createToast(String toastMessage) {
        Toast toast = Toast.makeText(getContext(),
                toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}