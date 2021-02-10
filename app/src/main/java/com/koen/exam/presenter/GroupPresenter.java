package com.koen.exam.presenter;

import com.koen.exam.model.GroupInfo;
import com.koen.exam.views.GlobalView;

import java.util.List;

public interface GroupPresenter extends GlobalPresenter {
    void findByName(String name);
    void finishSearchGroup(GroupInfo groupInfo);
    void joinInGroup(String name);
    void finishJoinInGroup(GroupInfo groupInfo);
    void getMyGroup();
    void finishGetMyGroup(List<GroupInfo> groupInfo);
}
