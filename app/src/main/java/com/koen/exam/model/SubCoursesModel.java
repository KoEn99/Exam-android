package com.koen.exam.model;

import java.util.List;

public class SubCoursesModel {
    String title;
    String id;
    String description;
    private List<ExamModel> examDtoList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExamModel> getExamDtoList() {
        return examDtoList;
    }
    public void setExamDtoList(List<ExamModel> examDtoList) {
        this.examDtoList = examDtoList;
    }
}
