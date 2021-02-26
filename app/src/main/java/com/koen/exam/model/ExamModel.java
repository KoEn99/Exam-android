package com.koen.exam.model;

public class ExamModel {
    private String title;
    private String description;
    private Integer timeWatch;
    private String statusType;
    private String coursesEntity;
    private Integer id;

    public ExamModel(String title, String description, Integer timeWatch, String statusType, String coursesEntity) {
        this.title = title;
        this.description = description;
        this.timeWatch = timeWatch;
        this.statusType = statusType;
        this.coursesEntity = coursesEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimeWatch() {
        return timeWatch;
    }

    public void setTimeWatch(Integer timeWatch) {
        this.timeWatch = timeWatch;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getCoursesEntity() {
        return coursesEntity;
    }

    public void setCoursesEntity(String coursesEntity) {
        this.coursesEntity = coursesEntity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
