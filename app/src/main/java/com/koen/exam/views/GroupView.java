package com.koen.exam.views;

import com.koen.exam.model.GroupInfo;

import java.util.List;

public interface GroupView extends GlobalView{
    void findByName(String name);
    void initialGroupAdapter(List<GroupInfo> groupInfoList);
    void adapterDataChanger(GroupInfo groupInfo);
    void showDialog(GroupInfo groupInfo);
}
