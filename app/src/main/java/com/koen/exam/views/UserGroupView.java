package com.koen.exam.views;

import com.koen.exam.model.GroupInfo;
import com.koen.exam.model.UserGroup;

import java.util.List;

public interface UserGroupView {
    void initialGroupAdapter(List<UserGroup> userGroups);
}
