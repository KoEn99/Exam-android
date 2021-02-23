package com.koen.exam.views.Impl;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.koen.exam.R;
import com.koen.exam.model.CourseInfo;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.presenter.GroupPresenter;
import com.koen.exam.presenter.Impl.GroupPresenterImpl;
import com.koen.exam.recycleAdapter.adapter.CourseAdapter;
import com.koen.exam.recycleAdapter.adapter.GroupAdapter;
import com.koen.exam.views.GroupView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainFragment extends Fragment implements GroupView {
    GroupPresenter groupPresenter;
    RecyclerView recyclerView;
    List<GroupInfo> groupInfoList;
    GroupAdapter groupAdapter;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleGroupView);
        NavigationActivity navigationActivity = (NavigationActivity)getActivity();
        groupPresenter = new GroupPresenterImpl(this);
        groupPresenter.getMyGroup();
        return view;
    }

    @Override
    public void createToast(String toastMessage) {
        Toast toast = Toast.makeText(getContext(),
                toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showFailMessage() {

    }

    @Override
    public void showSuccessMessage() {

    }

    @Override
    public void findByName(String name) {
        groupPresenter.findByName(name);
    }

    @Override
    public void initialGroupAdapter(List<GroupInfo> groupInfoList) {
        this.groupInfoList = groupInfoList;
        groupAdapter = new GroupAdapter(groupInfoList, getActivity());
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
        new MaterialAlertDialogBuilder(Objects.requireNonNull(getContext()))
                .setTitle(groupInfo.getCoursesEntity().getTitle())
                .setMessage(groupInfo.getCoursesEntity().getDescription().substring(0,
                        (int) (groupInfo.getCoursesEntity().getDescription().length()*0.75)) + "..." + "\n"
                + "Создатель: " + groupInfo.getCoursesEntity().getUserEntity().getFirstName() + " " +
                        groupInfo.getCoursesEntity().getUserEntity().getLastName() + " " +
                        groupInfo.getCoursesEntity().getUserEntity().getMiddleName() + " ")
                .setPositiveButton("Вступить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        groupPresenter.joinInGroup(groupInfo.getId().substring(0,4));
                    }
                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}