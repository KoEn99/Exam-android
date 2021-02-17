package com.koen.exam.service;

import com.koen.exam.model.GroupInfo;

public interface GroupService {
    void findByName(String name, String authToken);
    void joinInGroup(String name, String authToken);
    void getMyGroup(String authToken);
    void getGroupByCourse(String courseId, String authToken);
    void addGroup(GroupInfo groupInfo, String authToken);
    void getGroupUser(String groupName, String authToken);
}
