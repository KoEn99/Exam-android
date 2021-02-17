package com.koen.exam.views.Impl;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koen.exam.R;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.model.UserGroup;
import com.koen.exam.presenter.GroupPresenter;
import com.koen.exam.presenter.Impl.GroupPresenterImpl;
import com.koen.exam.recycleAdapter.adapter.GroupFromEditAdapter;
import com.koen.exam.recycleAdapter.adapter.UserGroupAdapter;
import com.koen.exam.views.GroupView;
import com.koen.exam.views.UserGroupView;

import java.util.List;

public class UserGroupFragment extends Fragment implements UserGroupView {
    View view;
    UserGroupAdapter userGroupAdapter;
    List<UserGroup> userGroups;
    RecyclerView recyclerView;
    GroupPresenter groupPresenter;
    String groupName;
    public UserGroupFragment(String groupName){
        this.groupName = groupName;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_group, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleGroupView);
        groupPresenter = new GroupPresenterImpl(this);
        groupPresenter.getUserGroup(groupName);
        return view;
    }

    @Override
    public void initialGroupAdapter(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
        userGroupAdapter = new UserGroupAdapter(userGroups, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(userGroupAdapter);
    }
}