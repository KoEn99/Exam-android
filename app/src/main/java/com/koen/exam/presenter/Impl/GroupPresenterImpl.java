package com.koen.exam.presenter.Impl;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.GroupInfo;
import com.koen.exam.model.UserGroup;
import com.koen.exam.presenter.GroupPresenter;
import com.koen.exam.service.CoursesService;
import com.koen.exam.service.GroupService;
import com.koen.exam.service.Impl.CoursesServiceImpl;
import com.koen.exam.service.Impl.GroupServiceImpl;
import com.koen.exam.views.CoursesView;
import com.koen.exam.views.GroupView;
import com.koen.exam.views.UserGroupView;

import java.util.List;

public class GroupPresenterImpl implements GroupPresenter {

    GroupView groupView;
    GroupService groupService;
    UserGroupView userGroupView;
    DataSingleton dataSingleton;
    public GroupPresenterImpl (GroupView groupView){
        this.groupView = groupView;
        groupService = new GroupServiceImpl(this);
        dataSingleton = DataSingleton.getInstance();
    }
    public GroupPresenterImpl (UserGroupView userGroupView){
        this.userGroupView = userGroupView;
        groupService = new GroupServiceImpl(this);
        dataSingleton = DataSingleton.getInstance();
    }

    @Override
    public void findByName(String name) {
        groupService.findByName(name, dataSingleton.jwtToken);
    }

    @Override
    public void finishSearchGroup(GroupInfo groupInfo) {
        groupView.showDialog(groupInfo);
    }

    @Override
    public void joinInGroup(String name) {
        groupService.joinInGroup(name, dataSingleton.jwtToken);
    }

    @Override
    public void finishJoinInGroup(GroupInfo groupInfo) {
        groupView.adapterDataChanger(groupInfo);
    }

    @Override
    public void getMyGroup() {
        groupService.getMyGroup(dataSingleton.jwtToken);
    }

    @Override
    public void finishGetMyGroup(List<GroupInfo> groupInfo) {
        groupView.initialGroupAdapter(groupInfo);
    }

    @Override
    public void getGroupByCourse(String courseId) {
        groupService.getGroupByCourse(courseId, dataSingleton.jwtToken);
    }

    @Override
    public void finishGetGroupByCourse(List<GroupInfo> groupInfo) {
        groupView.initialGroupAdapter(groupInfo);
    }

    @Override
    public void addGroup(GroupInfo groupInfo) {
        groupService.addGroup(groupInfo, dataSingleton.jwtToken);
    }

    @Override
    public void finishAddGroup(GroupInfo groupInfo) {
        groupView.adapterDataChanger(groupInfo);
    }

    @Override
    public void getUserGroup(String groupName) {
        groupService.getGroupUser(groupName, dataSingleton.jwtToken);
    }

    @Override
    public void finishGetUserGroup(List<UserGroup> userGroup) {
        userGroupView.initialGroupAdapter(userGroup);
    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void getMessageAnswer(String messageAnswer) {
        groupView.createToast(messageAnswer);
    }

    @Override
    public void listenerFinishError(String message) {
      //  getMessageAnswer(message.substring(message.indexOf("errorMessage") + 14,
        //message.indexOf("responseData") - 2));
    }

    @Override
    public void listenerFinish(GenericResponse<?> finishData) {

    }
}
