package com.koen.exam.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GroupInfo {
    private String id;
    private String title;
    private String description;
}
