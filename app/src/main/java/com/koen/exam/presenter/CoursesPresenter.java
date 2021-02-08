package com.koen.exam.presenter;

import com.koen.exam.model.CourseInfo;

public interface CoursesPresenter extends GlobalPresenter{
    void getMyCourses();
    void createMyCourse(CourseInfo courseInfo);
    void finishCreateMyCourse(CourseInfo courseInfo);
}
