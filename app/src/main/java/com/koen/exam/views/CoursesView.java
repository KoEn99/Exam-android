package com.koen.exam.views;

import com.koen.exam.model.CourseInfo;

import java.util.List;

public interface CoursesView extends GlobalView{
    void initialListCoursesAdapter(List<CourseInfo> courseInfoList);
    void adapterDataChanger(CourseInfo courseInfo);
}
