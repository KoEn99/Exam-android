package com.koen.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupInfo {
    private String id;
    private String name;
    private CourseInfo coursesEntity;
}
