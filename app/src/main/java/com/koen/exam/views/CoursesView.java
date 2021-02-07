package com.koen.exam.views;

import com.koen.exam.model.GroupInfo;

import java.util.List;

public interface CoursesView extends GlobalView{
    void initialListCoursesAdapter(List<GroupInfo> groupInfoList);
    void adapterDataChanger(GroupInfo groupInfo);
}
