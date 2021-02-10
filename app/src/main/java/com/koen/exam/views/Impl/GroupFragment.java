package com.koen.exam.views.Impl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.koen.exam.R;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.recycleAdapter.adapter.GroupAdapter;
import com.koen.exam.views.GroupView;

import java.util.List;

public class GroupFragment extends Fragment implements GroupView {
    GroupAdapter groupAdapter;
    List<GroupInfo> groupInfoList;
    RecyclerView recyclerView;
    View view;
    String idCourse;
    public GroupFragment(String idCourse){
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
        recyclerView = (RecyclerView)view.findViewById(R.id.recycleGroupView);
        Toast toast = Toast.makeText(getContext(),
                idCourse, Toast.LENGTH_SHORT);
        toast.show();
        return view;
    }

    @Override
    public void findByName(String name) {

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

    }

    @Override
    public void createToast(String toastMessage) {

    }
}