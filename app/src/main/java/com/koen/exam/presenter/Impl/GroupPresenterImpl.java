package com.koen.exam.presenter.Impl;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.presenter.GroupPresenter;
import com.koen.exam.service.CoursesService;
import com.koen.exam.service.GroupService;
import com.koen.exam.service.Impl.CoursesServiceImpl;
import com.koen.exam.service.Impl.GroupServiceImpl;
import com.koen.exam.views.CoursesView;
import com.koen.exam.views.GroupView;

public class GroupPresenterImpl implements GroupPresenter {

    GroupView groupView;
    GroupService groupService;
    DataSingleton dataSingleton;
    public GroupPresenterImpl (GroupView groupView){
        this.groupView = groupView;
        groupService = new GroupServiceImpl(this);
        dataSingleton = DataSingleton.getInstance();
    }

    @Override
    public void findByName(String name) {
        groupService.findByName(name, dataSingleton.jwtToken);
    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void getMessageAnswer(String messageAnswer) {

    }

    @Override
    public void listenerFinishError(String message) {

    }

    @Override
    public void listenerFinish(GenericResponse<?> finishData) {

    }
}
