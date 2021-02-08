package com.koen.exam.service;

import com.koen.exam.model.CourseInfo;

public interface CoursesService {
    void getMyCourses(String authToken);
    void crateMyCourse(CourseInfo courseInfo, String authToken);
}
