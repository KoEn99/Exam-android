package com.koen.exam.views.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.koen.exam.R;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.presenter.GroupPresenter;
import com.koen.exam.presenter.Impl.GroupPresenterImpl;
import com.koen.exam.recycleAdapter.adapter.GroupFromEditAdapter;
import com.koen.exam.views.GroupView;
import com.koen.exam.views.dialogs.SheetCreateGroup;

import java.util.List;

public class GroupFragment extends Fragment implements GroupView {
    GroupFromEditAdapter groupAdapter;
    List<GroupInfo> groupInfoList;
    RecyclerView recyclerView;
    View view;
    String idCourse;
    GroupPresenter groupPresenter;
    FloatingActionButton floatingActionButton;

    public GroupFragment(String idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_group, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleGroupView);
        groupPresenter = new GroupPresenterImpl(this);
        groupPresenter.getGroupByCourse(idCourse);
        NavigationActivity navigationActivity = (NavigationActivity)getActivity();
        navigationActivity.visibleArrow();
        navigationActivity.getSupportActionBar().setTitle("Группы");
        floatingActionButton = (FloatingActionButton)navigationActivity.findViewById(R.id.fab);
        navigationActivity.bottomAppBar.performShow();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SheetCreateGroup sheetsCreateGroup = new SheetCreateGroup(GroupFragment.this, idCourse);
                sheetsCreateGroup.show(navigationActivity.getSupportFragmentManager(), "TAG");
                navigationActivity.backgroundNavigation.setVisibility(View.INVISIBLE);
            }
        });
        return view;
    }

    @Override
    public void findByName(String name) {

    }

    @Override
    public void initialGroupAdapter(List<GroupInfo> groupInfoList) {
        this.groupInfoList = groupInfoList;
        groupAdapter = new GroupFromEditAdapter(groupInfoList, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(groupAdapter);
    }

    @Override
    public void adapterDataChanger(GroupInfo groupInfo) {
        groupInfoList.add(groupInfo);
        groupAdapter.dataChanged(groupInfoList);
    }

    @Override
    public void showDialog(GroupInfo groupInfo) {

    }

    @Override
    public void createToast(String toastMessage) {

    }

    @Override
    public void showFailMessage() {

    }

    @Override
    public void showSuccessMessage() {

    }
}