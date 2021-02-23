package com.koen.exam.model;

public class ExamModel {
    private String title;
    private String description;
    private Integer timeWatch;
    private String statusType;
    private String coursesEntity;

    public ExamModel(String tittle, String description, Integer timeWatch, String statusType, String coursesEntity) {
        this.title = tittle;
        this.description = description;
        this.timeWatch = timeWatch;
        this.statusType = statusType;
        this.coursesEntity = coursesEntity;
    }

    public String getTittle() {
        return title;
    }

    public void setTittle(String tittle) {
        this.title = tittle;
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
}
