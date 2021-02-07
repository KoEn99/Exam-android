package com.koen.exam.presenter;

import com.koen.exam.model.GroupInfo;

public interface CoursesPresenter extends GlobalPresenter{
    void getMyCourses();
    void createMyCourse(GroupInfo groupInfo);
    void finishCreateMyCourse(GroupInfo groupInfo);
}
