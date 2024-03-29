package com.koen.exam.presenter.Impl;

import com.koen.exam.DataSingleton;
import com.koen.exam.model.GenericResponse;
import com.koen.exam.model.CourseInfo;
import com.koen.exam.presenter.CoursesPresenter;
import com.koen.exam.service.CoursesService;
import com.koen.exam.service.Impl.CoursesServiceImpl;
import com.koen.exam.views.CoursesView;

import java.util.List;

public class CoursesPresenterImpl implements CoursesPresenter {

    CoursesView coursesView;
    CoursesService coursesService;
    DataSingleton dataSingleton;
    public CoursesPresenterImpl (CoursesView coursesView){
        this.coursesView = coursesView;
        coursesService = new CoursesServiceImpl(this);
        dataSingleton = DataSingleton.getInstance();
    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void getMessageAnswer(String messageAnswer) {
        coursesView.createToast(messageAnswer);
    }

    @Override
    public void listenerFinishError(String message) {

    }

    @Override
    public void listenerFinish(GenericResponse<?> finishData) {
        coursesView.initialListCoursesAdapter((List<CourseInfo>) finishData.getResponseData());
    }

    @Override
    public void getMyCourses() {
        coursesService.getMyCourses(dataSingleton.jwtToken);
    }

    @Override
    public void createMyCourse(CourseInfo courseInfo) {
        coursesService.crateMyCourse(courseInfo, dataSingleton.jwtToken);
    }

    @Override
    public void finishCreateMyCourse(CourseInfo courseInfo) {
        getMessageAnswer("Курс успешно создан");
        coursesView.adapterDataChanger(courseInfo);
    }
}
