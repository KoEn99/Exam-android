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
        initialGroupAdapter(new ArrayList<>());
        new MaterialAlertDialogBuilder(getActivity()).setTitle("qwer")
                .setMessage("dsadas").setPositiveButton("ОК, иду на кухню", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Закрываем окно
                dialog.cancel();
            }
        });
        return view;
    }

    @Override
    public void createToast(String toastMessage) {

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
}