package com.koen.exam.service;

import com.koen.exam.model.GroupInfo;

public interface CoursesService {
    void getMyCourses(String authToken);
    void crateMyCourse(GroupInfo groupInfo, String authToken);
}
